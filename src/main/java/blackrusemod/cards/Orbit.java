package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ConvertAction;
import blackrusemod.actions.OrbitDamageAction;
import blackrusemod.cards.Interfaces.KnivesCard;
import blackrusemod.patches.AbstractCardEnum;

public class Orbit extends CustomCard implements KnivesCard {
	public static final String ID = "BlackRuseMod:Orbit";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 3;
	private static final int SATELLITE = 3;
	
	public Orbit() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.ORBIT), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = SATELLITE;
		this.isMultiDamage = true;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new ConvertAction(this.magicNumber));
		addToBot(new OrbitDamageAction(this.multiDamage));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Orbit();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}