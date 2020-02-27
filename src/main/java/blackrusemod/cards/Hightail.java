package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.HightailAction;
import blackrusemod.powers.ElegancePower;

public class Hightail extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Hightail.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("hightail.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = -1;
	private static final int PROTECTION = 8;

	public Hightail() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.magicNumber = this.baseMagicNumber = PROTECTION;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		addToBot(new HightailAction(p, this.magicNumber, this.freeToPlayOnce, this.energyOnUse));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Hightail();
	}
	
	@Override
	public void applyPowers() {
		this.magicNumber = this.baseMagicNumber = PROTECTION;
		if (!this.canUpgrade()) upgradeMagicNumber(3);
		if (AbstractDungeon.player.hasPower(ElegancePower.POWER_ID)) {
			upgradeMagicNumber(AbstractDungeon.player.getPower(ElegancePower.POWER_ID).amount);
			this.isMagicNumberModified = true;
		}
		super.applyPowers();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(3);
		}
	}
}