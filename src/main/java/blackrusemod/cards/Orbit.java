package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ConvertAction;
import blackrusemod.actions.OrbitDamageAction;
import blackrusemod.patches.AbstractCardEnum;

public class Orbit extends CustomCard {
	public static final String ID = "Orbit";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 4;
	private static final int SATELLITE = 3;
	
	public Orbit() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.ORBIT), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ALL_ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = SATELLITE;
		this.isMultiDamage = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ConvertAction(this.magicNumber));
		AbstractDungeon.actionManager.addToBottom(new OrbitDamageAction(this.multiDamage));
			
	}

	public AbstractCard makeCopy() {
		return new Orbit();
	}
	
	public void applyPowers() {
		this.baseDamage = ATTACK_DMG;
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) 
			this.baseDamage += AbstractDungeon.player.getPower("SilverBladesPower").amount;
		super.applyPowers();
		if (AbstractDungeon.player.hasPower("SilverBladesPower"))
			this.isDamageModified = true;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(1);
		}
	}
}