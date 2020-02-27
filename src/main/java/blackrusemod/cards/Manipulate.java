package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ManipulateAction;

public class Manipulate extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Manipulate.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("manipulate.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = -1;
	
	public Manipulate() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		addToBot(new ManipulateAction(p, this.freeToPlayOnce, this.energyOnUse, this.canUpgrade()));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Manipulate();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}