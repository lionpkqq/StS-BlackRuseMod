/*    */ package blackrusemod.actions;

		 import com.megacrit.cardcrawl.actions.AbstractGameAction;
		 import com.megacrit.cardcrawl.actions.utility.WaitAction;
/*    */ import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
/*    */ import com.megacrit.cardcrawl.core.AbstractCreature;
		 import com.megacrit.cardcrawl.core.CardCrawlGame;
/*    */ import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
/*    */ import com.megacrit.cardcrawl.localization.UIStrings;

import blackrusemod.patches.RealityMarblePatch;
/*    */ 
/*    */ public class RealityMarbleAction extends AbstractGameAction
/*    */ {
/* 13 */   private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("RetainCardsAction");
/* 14 */   public static final String[] TEXT = uiStrings.TEXT;
/*    */   
/*    */   public RealityMarbleAction(AbstractCreature source, int amount) {
/* 17 */     setValues(AbstractDungeon.player, source, amount);
/* 18 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*    */   }
/*    */   
/*    */ 
/*    */   public void update()
/*    */   {
/* 24 */     if (this.duration == 0.5F) {
/* 25 */       AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, true, false, false, true);
/* 26 */       AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
/* 27 */       tickDuration();
/* 28 */       return;
/*    */     }
/*    */ 
/* 32 */     if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved)
/*    */     {
/* 34 */       for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
/* 36 */         c.retain = true;
				 boolean wasEthereal = c.isEthereal;
				 c.isEthereal = false;
				 if(wasEthereal) {
					 RealityMarblePatch.marbledField.blackrusemod_marbled.set(c, true);
					 c.initializeDescription();
				 }
/* 38 */         AbstractDungeon.player.hand.addToTop(c);
/*    */       }
/* 40 */       AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
/*    */     }
/*    */     
/* 43 */     tickDuration();
/*    */   }
/*    */ }
