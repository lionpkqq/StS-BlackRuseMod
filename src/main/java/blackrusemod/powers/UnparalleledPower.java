package blackrusemod.powers;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import blackrusemod.BlackRuseMod;

public class UnparalleledPower extends AbstractPower {
	public static final String POWER_ID = "UnparalleledPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	public static TextureAtlas powerAltas = BlackRuseMod.getPowerTextureAtlas();
	private boolean paralleled = false;
	
	public UnparalleledPower(AbstractCreature owner, int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = owner;
		this.amount = amount;
		this.paralleled = false;
		updateDescription();
		this.region48 = powerAltas.findRegion("unparalleled48");
		this.region128 = powerAltas.findRegion("unparalleled128");
	}
	
	public void stackPower(int stackAmount)
	{
		this.fontScale = 8.0F;
		this.amount += stackAmount;
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		if (!paralleled) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, stackAmount), stackAmount));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, stackAmount), stackAmount));
		}
		else {
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "怎么……我的卡组里有消耗2的卡！", 1.0F, 2.0F));
			}
			else {
			AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Oops, I have card(s) that cost 2!", 1.0F, 2.0F));
			}
		}
		this.paralleled = false;
	}
	
	public void onInitialApplication() {
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		for (AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
			 if (c.cost == 2) paralleled = true;
		}
		if (!paralleled) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new DexterityPower(this.owner, this.amount), this.amount));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
		}
		else {
			if (Settings.language == GameLanguage.ZHS || Settings.language == GameLanguage.ZHT) {
				AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "怎么……我的卡组里有消耗2的卡！", 1.0F, 2.0F));
			}
			else {
			AbstractDungeon.actionManager.addToBottom(new TalkAction(true, "Oops, I have card(s) that cost 2!", 1.0F, 2.0F));
			}
		}
		this.paralleled = false;
	}

	public void updateDescription()
	{
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
}