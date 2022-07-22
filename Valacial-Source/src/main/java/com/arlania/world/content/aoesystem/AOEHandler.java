package com.arlania.world.content.aoesystem;

import java.util.Iterator;

import com.arlania.GameSettings;
import com.arlania.model.CombatIcon;
import com.arlania.model.Hit;
import com.arlania.model.Hitmask;
import com.arlania.model.Locations;
import com.arlania.util.RandomUtility;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class AOEHandler {

	public static void handleAttack(Character attacker, Character victim, int minimumDamage, int maximumDamage,
			int radius, CombatIcon combatIcone) {

		// We passed the checks, so now we do multiple target stuff.
		Iterator<? extends Character> it = null;
		if (attacker.isPlayer() && victim.isPlayer()) {
			it = ((Player) attacker).getLocalPlayers().iterator();
		} else if (attacker.isPlayer() && victim.isNpc()) {
			it = ((Player) attacker).getLocalNpcs().iterator();

			for (Iterator<? extends Character> $it = it; $it.hasNext();) {
				Character next = $it.next();

				if (next == null) {
					continue;
				}

				if (next.isNpc()) {
					NPC n = (NPC) next;
					if (!n.getDefinition().isAttackable() || n.isSummoningNpc()) {
					    ((NPC) next).getDefinition().setAggressive(true);
						continue;
					}
				} else {
					Player p = (Player) next;
					if (p.getLocation() != Locations.Location.WILDERNESS || !Locations.Location.inMulti(p)) {
						continue;
					}
				}
				if (next.getPosition().isWithinDistance(victim.getPosition(), radius) && !next.equals(attacker) && !next.equals(victim) && next.getConstitution() > 0) {
					
					Hitmask mask = null;
					
					int calc = RandomUtility.inclusiveRandom(minimumDamage, maximumDamage);
					
					if (combatIcone == CombatIcon.MELEE)
						mask = Hitmask.DARK_RED;
					else if (combatIcone == CombatIcon.RANGED)
						mask = Hitmask.DARK_GREEN;
					else
						mask = Hitmask.DARK_PURPLE;
					
					
					next.dealDamage(new Hit(calc, mask, combatIcone));
					next.getCombatBuilder().addDamage(attacker, calc);
					}
					
				}
			}
		}

	}
