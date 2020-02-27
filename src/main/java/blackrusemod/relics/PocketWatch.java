package blackrusemod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;
import blackrusemod.BlackRuseMod;
import blackrusemod.util.TextureLoader;

import static blackrusemod.BlackRuseMod.makeRelicPath;
import static blackrusemod.BlackRuseMod.makeRelicOutlinePath;

public class PocketWatch extends CustomRelic {
	public static final String ID = BlackRuseMod.makeID(PocketWatch.class.getSimpleName());
	private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("pocket_watch.png"));
	private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("pocket_watch.png"));
	
	public PocketWatch() {
		super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
	}
	
	@Override
	public void onExhaust(AbstractCard card) {
		flash();
		addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
		addToBot(new DrawCardAction(AbstractDungeon.player, 1));
	}
	
	@Override
	public String getUpdatedDescription() {
		return this.DESCRIPTIONS[0];
	}
	
	@Override
	public AbstractRelic makeCopy() {
		return new PocketWatch();
	}
}