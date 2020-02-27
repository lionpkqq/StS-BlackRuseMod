package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.powers.EmbodimentPower;
import blackrusemod.powers.UpgradedEmbodimentPower;

public class Embodiment extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Embodiment.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("embodiment.png");
	private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
	private static final int COST = 2;

	public Embodiment() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!this.upgraded) addToBot(new ApplyPowerAction(p, p, new EmbodimentPower(p, 1), 1));
		else addToBot(new ApplyPowerAction(p, p, new UpgradedEmbodimentPower(p, 1), 1));
	}

	public AbstractCard makeCopy() {
		return new Embodiment();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}