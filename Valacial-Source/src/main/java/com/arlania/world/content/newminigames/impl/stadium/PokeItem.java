package com.arlania.world.content.newminigames.impl.stadium;

import com.arlania.world.content.dialogue.Dialogue;
import com.arlania.world.content.dialogue.DialogueExpression;
import com.arlania.world.content.dialogue.DialogueType;
import com.arlania.world.content.newminigames.impl.stadium.pokeitemimpl.PPPotion;
import com.arlania.world.content.newminigames.impl.stadium.pokeitemimpl.SuperPotion;

public abstract class PokeItem {
	
	public abstract void execute(Pokemon pokemon);
	
	public abstract String getDescription();
	
	public Dialogue getDialogue() {
		if(this.getDescription() != null && !this.getDescription().equals("")) {
			
			Dialogue itemDialogue = new Dialogue() {

				@Override
				public DialogueType type() {
					return DialogueType.STATEMENT;
				}

				@Override
				public DialogueExpression animation() {
					return DialogueExpression.NO_EXPRESSION;
				}

				@Override
				public String[] dialogue() {
					return new String[] { PokeItem.this.getDescription() };
				}
				
			};
			return itemDialogue;
		}
		return null;
	}
	
	public enum PokeItemInfo {

		PPPOTION(20030, new PPPotion()), SUPER_POTION(20026, new SuperPotion());

		PokeItemInfo(int itemId, PokeItem pokeItem) {
			this.itemId = itemId;
			this.pokeItem = pokeItem;
		}

		private int itemId;
		private PokeItem pokeItem;
		
		public int getItemId() {
			return this.itemId;
		}
		
		public PokeItem getPokeItem() {
			return this.pokeItem;
		}

		public static PokeItemInfo forId(int itemId) {
			for (PokeItemInfo item : PokeItemInfo.values()) {
				if (item.itemId == itemId)
					return item;
			}
			return null;
		}
	}

}
