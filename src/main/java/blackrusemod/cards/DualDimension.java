package blackrusemod.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.DoubleProtectionAction;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.ElegancePower;
import blackrusemod.powers.ProtectionPower;
import blackrusemod.relics.KneeBrace;

public class DualDimension extends AbstractShiftCard {
	public static final String ID = "BlackRuseMod:DualDimension";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int COST_UPGRADED = 1;
	private static final int PROTECTION = 6;

	public DualDimension() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.DUAL_DIMENSION), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = PROTECTION;
		this.exhaust = true;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new VerticalAuraEffect(new Color(0.0F, 1.0F, 1.0F, 0.0F), p.hb.cX, p.hb.cY)));
		AbstractDungeon.actionManager.addToBottom(new DoubleProtectionAction(p));
	}
	
	public void triggerShift() {
		this.applyPowers();
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new ProtectionPower(AbstractDungeon.player, this.magicNumber), this.magicNumber));
	}
	
	public void applyPowers() {
		this.magicNumber = this.baseMagicNumber = PROTECTION;
		if (AbstractDungeon.player.hasPower(ElegancePower.POWER_ID)) {
			upgradeMagicNumber(AbstractDungeon.player.getPower(ElegancePower.POWER_ID).amount);
			this.isMagicNumberModified = true;
		}
		super.applyPowers();
	}

	public AbstractCard makeCopy() {
		return new DualDimension();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}