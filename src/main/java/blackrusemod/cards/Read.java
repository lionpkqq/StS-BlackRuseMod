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
import blackrusemod.powers.ElegancePower;

public class Read extends CustomCard {
	public static final String ID = "BlackRuseMod:Read";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int BLOCK = 15;
	private static final int UPGRADE_PLUS_BLOCK = 5;
	private static final int WEAK = 2;

	public Read() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.READ), COST, DESCRIPTION, AbstractCard.CardType.SKILL,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = WEAK;
	}

	@Override
	public void applyPowersToBlock() {
		this.block = this.baseBlock;
		if (AbstractDungeon.player.hasPower(ElegancePower.POWER_ID)) {
			this.block += AbstractDungeon.player.getPower(ElegancePower.POWER_ID).amount;
			this.isBlockModified = true;
		}
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot(new VisionAction(p, m, this.block, this.magicNumber, this));
	}

	@Override
	public AbstractCard makeCopy() {
		return new Read();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(1);
		}
	}
}