package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.GougeAction;

public class Gouge extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Gouge.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("gouge.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = -1;
	private static final int ATTACK_DMG = 6;

	public Gouge() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		addToBot(new GougeAction(p, m, this.damage, this.damageTypeForTurn, this.freeToPlayOnce, this.energyOnUse, this.canUpgrade()));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Gouge();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}