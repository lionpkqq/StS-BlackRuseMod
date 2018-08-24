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

public class Snipe extends CustomCard {
	public static final String ID = "Snipe";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int ATTACK_DMG = 15;
	private static final int UPGRADE_PLUS_DMG = 5;
	private static final int VUL = 1;

	public Snipe() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SNIPE), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = VUL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new VisionAction(p, m, this.baseDamage, this.magicNumber, this));
	}

	public AbstractCard makeCopy() {
		return new Snipe();
	}
	
	public void applyPowers() {
		if (canUpgrade()) this.baseDamage = ATTACK_DMG;
		else this.baseDamage = ATTACK_DMG + UPGRADE_PLUS_DMG;
		if (AbstractDungeon.player.hasPower("SilverBladesPower")) 
			this.baseDamage += AbstractDungeon.player.getPower("SilverBladesPower").amount;
		super.applyPowers();
		if (AbstractDungeon.player.hasPower("SilverBladesPower"))
			this.isDamageModified = true;
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}