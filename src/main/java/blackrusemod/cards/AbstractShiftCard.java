package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import blackrusemod.relics.KneeBrace;

public class AbstractShiftCard extends CustomCard {
	private boolean inDiscardMenu = false;

	public AbstractShiftCard(String id, String name, String texture, int cost, String desc, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
		super(id, name, texture, cost, desc, type, color, rarity, target);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if ((p != null) && (m != null)) 
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 
				AbstractGameAction.AttackEffect.SLASH_VERTICAL));
		AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
	}
	
	public void triggerOnManualDiscard() {
		triggerShift();
		if (AbstractDungeon.player.hasRelic(KneeBrace.ID)) {
			AbstractDungeon.player.getRelic(KneeBrace.ID).flash();
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, KneeBrace.BLOCK_AMT));
		}
	}
	
	public void triggerShift() {}
	
	// Glow gold during discard actions to indicate Shift
	// Changes to blue if selected in a grid screen to show being selected
	public void update() {
		super.update();
		AbstractGameAction currAction = AbstractDungeon.actionManager.currentAction;
		if(currAction != null && currAction.actionType == ActionType.DISCARD
				&& ((AbstractDungeon.screen == CurrentScreen.HAND_SELECT && (AbstractDungeon.player.hand.contains(this) || AbstractDungeon.handCardSelectScreen.selectedCards.contains(this)))
				|| AbstractDungeon.screen == CurrentScreen.GRID && AbstractDungeon.gridSelectScreen.targetGroup.contains(this) && !AbstractDungeon.gridSelectScreen.selectedCards.contains(this))) {
			this.beginGlowing();
			this.glowColor = GOLD_BORDER_GLOW_COLOR;
			this.inDiscardMenu = true;
		} else if(this.inDiscardMenu) {
			this.glowColor = BLUE_BORDER_GLOW_COLOR;
			this.inDiscardMenu = false;
		}
	}

	public void upgrade() {}
}