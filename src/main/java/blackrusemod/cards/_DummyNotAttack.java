package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.AutoAdd;
import blackrusemod.BlackRuseMod;

@AutoAdd.Ignore
public class _DummyNotAttack extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(_DummyNotAttack.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("_dummy_not_attack.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
	private static final int COST = -2;

	public _DummyNotAttack() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {}

	@Override
	public AbstractCard makeCopy() {
		return new _DummyNotAttack();
	}

	@Override
	public void upgrade() {}
}