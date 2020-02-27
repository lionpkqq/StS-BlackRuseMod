package blackrusemod.cards;

import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import blackrusemod.BlackRuseMod;
import blackrusemod.actions.ThrowKnifeAction;

public class FanOfKnives extends AbstractServantCard {
	public static final String ID = BlackRuseMod.makeID(FanOfKnives.class.getSimpleName());
	public static final String IMG = BlackRuseMod.makeCardPath("fan_of_knives.png");
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
	private static final int COST = -1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 3;

	public FanOfKnives() {
		super(ID, IMG, COST, TYPE, RARITY, TARGET);
		this.baseDamage = ATTACK_DMG;
		this.isMultiDamage = true;
		this.tags.add(Enums.SILVER_BLADES);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (this.energyOnUse < EnergyPanel.totalCount) {
			this.energyOnUse = EnergyPanel.totalCount;
		}
		int effect = this.energyOnUse;
		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			effect += 2;
		}
		for (int i = 0; i < effect; i++) {
			for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
				addToBot(new ThrowKnifeAction(p, mo, new DamageInfo(p, this.damage, this.damageTypeForTurn), null, false));
			}
		}
		addToBot(new LoseEnergyAction(this.energyOnUse));
	}

	@Override
	public AbstractCard makeCopy() {
		return new FanOfKnives();
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
		}
	}
}