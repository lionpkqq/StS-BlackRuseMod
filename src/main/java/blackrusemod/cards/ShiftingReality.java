package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;

public class ShiftingReality extends CustomCard {
	public static final String ID = "ShiftingReality";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 5;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int ALL = 10;
	private static final int UPGRADE_PLUS_ALL = 6;
	private DamageInfo info;

	public ShiftingReality() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.SHIFTING_REALITY), COST, DESCRIPTION, AbstractCard.CardType.ATTACK,
				AbstractCardEnum.SILVER, AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ALL;
	}

	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	
	public void triggerOnManualDiscard() {
		this.info = new DamageInfo (AbstractDungeon.player, this.magicNumber, this.damageTypeForTurn);
		
		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.isDeadOrEscaped()) {
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
				this.info.applyPowers(AbstractDungeon.player, mo);
				AbstractDungeon.actionManager.addToBottom(new DamageAction(mo, this.info, AbstractGameAction.AttackEffect.NONE));
			}
		}
		
		if (AbstractDungeon.player.hasRelic("KneeBrace")) 
			AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 3));
	}

	public AbstractCard makeCopy() {
		return new ShiftingReality();
	}

	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_ALL);
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}