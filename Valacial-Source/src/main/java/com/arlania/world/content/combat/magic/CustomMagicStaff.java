package com.arlania.world.content.combat.magic;


import com.arlania.model.Animation;
import com.arlania.model.Graphic;
import com.arlania.model.Position;
import com.arlania.model.container.impl.Equipment;
import com.arlania.world.content.combat.CombatContainer;
import com.arlania.world.content.combat.CombatType;
import com.arlania.world.entity.impl.Character;
import com.arlania.world.entity.impl.npc.NPC;
import com.arlania.world.entity.impl.player.Player;

public class CustomMagicStaff {

    public static enum CustomStaff {
    	
        KRYPTIC_STAFF(new int[] { 13215 }, CombatSpells.KYRPTIC_SPELL.getSpell()),
        OBLIVIONSTAFF(new int[] { 938 }, CombatSpells.OBLIVION.getSpell()),
        WATER_STAFF(new int[] { 20604 }, CombatSpells.WATER_STAFF.getSpell()),
        Infernal_Staff(new int[] { 20602 }, CombatSpells.Infernal_STAFF.getSpell()),
        MADARA(new int[] { 4056 }, CombatSpells.MADARA.getSpell()),
        MADARAX(new int[] { 3084 }, CombatSpells.MADARAX.getSpell()),
        INFIN1(new int[] { 4060 }, CombatSpells.INFIN1.getSpell()),
        INFIN2(new int[] { 4061 }, CombatSpells.INFIN2.getSpell()),
        INVIC(new int[] { 14924 }, CombatSpells.INVIC.getSpell()),
        WINTER4EVER(new int[] { 8898 }, CombatSpells.WINTER4EVER.getSpell()),
    	DEXION(new int[] { 3246 }, CombatSpells.DEXION.getSpell()),
    	VOLDEMORT(new int[] { 5157 }, CombatSpells.VOLDEMORT.getSpell()),
       	DEATH(new int[] { 3067 }, CombatSpells.DEATH.getSpell()),
    	//MYSTERIO(new int[] { 5202 }, CombatSpells.DEATH.getSpell()),
    	INSANE_POWER(new int[] { 903 }, CombatSpells.INSANE_POWER.getSpell());
    	

        private int[] itemIds;
        private CombatSpell spell;

        CustomStaff(int[] itemIds, CombatSpell spell) {
            this.itemIds = itemIds;
            this.spell = spell;
        }

        public int[] getItems() {
            return this.itemIds;
        }

        public CombatSpell getSpell() {
            return this.spell;
        }

        public static CombatSpell getSpellForWeapon(int weaponId) {
            for (CustomStaff staff : CustomStaff.values()) {
                for (int itemId : staff.getItems())
                    if (weaponId == itemId)
                        return staff.getSpell();
            }
            return null;
        }
    }

    public static boolean checkCustomStaff(Character c) {
        int weapon;
        if (!c.isPlayer())
            return false;
        Player player = (Player)c;
        weapon = player.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
        return CustomStaff.getSpellForWeapon(weapon) != null;
    }

    public static void handleCustomStaff(Character c) {
        Player player = (Player) c;
        int weapon = player.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId();
        CombatSpell spell = CustomStaff.getSpellForWeapon(weapon);
        player.setCastSpell(spell);
        player.setAutocast(true);
        player.setAutocastSpell(spell);
        player.setCurrentlyCasting(spell);
        player.setLastCombatType(CombatType.MAGIC);

    }
    public static CombatContainer getCombatContainer(Character player, Character target) {
        ((Player)player).setLastCombatType(CombatType.MAGIC);
        return new CombatContainer(player, target, 1, 1, CombatType.MAGIC, true) {
            @Override
            public void onHit(int damage, boolean accurate) {

                target.performGraphic(new Graphic(1730));
            }
        };
    }

}
