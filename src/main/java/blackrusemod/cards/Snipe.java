package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.powers.SnipePower;

public class Snipe extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(Snipe.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("snipe.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 15;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int VUL = 1;

	public Snipe() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = VUL;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, new SnipePower(m, this.damage, this.magicNumber)));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Snipe();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}