package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.BaseModCardTags;
import blackrusemod.BlackRuseMod;
import blackrusemod.powers.FlawlessFormPower;
import blackrusemod.powers.ProtectionPower;

public class FlawlessForm extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FlawlessForm.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("flawless_form.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 3;
	private static final int PROTECTION_AMT = 12;

	public FlawlessForm() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.protection = this.baseProtection = PROTECTION_AMT;
		this.tags.add(BaseModCardTags.FORM);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ApplyPowerAction(p, p, new ProtectionPower(p, this.protection), this.protection));
		addToBot(new ApplyPowerAction(p, p, new FlawlessFormPower(p, -1), -1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FlawlessForm();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			this.selfRetain = true;
			upgradeProtection(4);
		}
	}
}