package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.BacklashAction;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class MysterySword extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(MysterySword.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("mystery_sword.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("mystery_sword.png"));
	public boolean active = true;
	
	public MysterySword() {
		super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.CLINK);
	}
	
	@Override
	public void onUseCard(AbstractCard card, UseCardAction action) {
		if (card.type == AbstractCard.CardType.SKILL  && this.active) {
			this.active = false;
			flash();
			stopPulse();
			addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
			addToBot(new BacklashAction(1));
		}
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public void onEquip() {
		AbstractDungeon.player.energy.energyMaster += 1;
	}

	@Override
	public void onUnequip() {
		AbstractDungeon.player.energy.energyMaster -= 1;
	}
	
	@Override
	public void atTurnStart() {
		beginLongPulse();
		this.active = true;
	}

	@Override
	public boolean checkTrigger() {
		return this.active;
	}
	
	@Override
	public void onVictory() {
		stopPulse();
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new MysterySword();
	}
}