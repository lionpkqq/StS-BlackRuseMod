package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.IronWaveEffect;

import blackrusemod.BlackRuseMod;

public class TimeWarp extends AbstractShiftCard {
	public static final String ID = BlackRuseMod.makeID(TimeWarp.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("time_warp.png");
	private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = 0;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int DISCARD_AND_DRAW = 1;

	public TimeWarp() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DISCARD_AND_DRAW;
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (p != null && m != null) {
			addToBot(new VFXAction(new IronWaveEffect(p.hb.cX, p.hb.cY, m.hb.cX), 0.5F));
		}
		addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_VERTICAL));
		addToBot(new DiscardAction(p, p, this.magicNumber, false));
	}
	
	@Override
	public void triggerShift() {
		addToBot(new DrawCardAction(AbstractDungeon.player, 1));
	}

	@Override
	public AbstractCard makeCopy() {
		return new TimeWarp();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = this.strings.UPGRADE_DESCRIPTION;
			initializeDescription();
			upgradeMagicNumber(1);
		}
	}
}