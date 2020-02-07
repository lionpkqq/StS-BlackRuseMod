package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class FastForward extends CustomCard {
	public static final String ID = "BlackRuseMod:FastForward";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 9;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int DRAW = 2;
	private boolean ATTACK = false;
	
	public FastForward() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.FAST_FORWARD), COST, DESCRIPTION,
				AbstractCard.CardType.SKILL, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.ATTACK = false;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			if (c.type == AbstractCard.CardType.ATTACK)
				this.ATTACK = true;
		}
		if (this.ATTACK == false)
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
		this.ATTACK = false;
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FastForward();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
		}
	}
}