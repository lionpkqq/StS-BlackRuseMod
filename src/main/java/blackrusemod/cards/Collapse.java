package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;
import blackrusemod.BlackRuseMod;
import blackrusemod.patches.AbstractCardEnum;
import blackrusemod.powers.ProtectionPower;

public class Collapse extends CustomCard {
	public static final String ID = "BlackRuseMod:Collapse";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    private static final int COST = 2;
    private static final int COST_UPGRADED = 1;
	
	public Collapse() {
		super(ID, NAME, BlackRuseMod.makePath(BlackRuseMod.COLLAPSE), COST, DESCRIPTION,
				AbstractCard.CardType.ATTACK, AbstractCardEnum.SILVER,
				AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.ALL_ENEMY);
		this.isMultiDamage = true;
		this.baseDamage = 0;
	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p.hasPower(ProtectionPower.POWER_ID)) 
			this.baseDamage = p.getPower(ProtectionPower.POWER_ID).amount;
		else this.baseDamage = 0;
		calculateCardDamage(m);
		AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.FIRE));
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}
	
	public void applyPowers()
	{
		if (AbstractDungeon.player.hasPower(ProtectionPower.POWER_ID))
			this.baseDamage = AbstractDungeon.player.getPower(ProtectionPower.POWER_ID).amount;
		else this.baseDamage = 0;
		super.applyPowers();

		this.rawDescription = DESCRIPTION;
		this.rawDescription += EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	public void onMoveToDiscard()
	{
		this.rawDescription = DESCRIPTION;
		initializeDescription();
	}

	public void calculateCardDamage(AbstractMonster mo)
	{
		super.calculateCardDamage(mo);
		this.rawDescription = DESCRIPTION;
		this.rawDescription += EXTENDED_DESCRIPTION[0];
		initializeDescription();
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new Collapse();
	}
	
	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBaseCost(COST_UPGRADED);
		}
	}
}