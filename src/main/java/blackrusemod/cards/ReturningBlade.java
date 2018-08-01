package blackrusemod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.actions.VisionAction;
import blackrusemod.patches.AbstractCardEnum;

public class ReturningBlade extends CustomCard {
	public static final String ID = "ReturningBlade";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	
	public ReturningBlade()
	{
		this(0);
	}

	public ReturningBlade(int upgrades) {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.RETURNING_BLADE), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.timesUpgraded = upgrades;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VisionAction(p, m, this.baseDamage, 0, this));
	}

	public AbstractCard makeCopy() {
		return new ReturningBlade(this.timesUpgraded);
	}
	
	public void upgrade()
	{
		upgradeDamage(2);
		this.timesUpgraded += 1;
		this.upgraded = true;
		this.name = (NAME + "+" + this.timesUpgraded);
		initializeTitle();
	}

	public boolean canUpgrade()
	{
		return true;
	}
}