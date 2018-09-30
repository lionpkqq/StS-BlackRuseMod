package blackrusemod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/*    */ public class RevampAction extends AbstractGameAction
/*    */ {
/*    */   private AbstractPlayer p;
/*    */   
/*    */   public RevampAction(int amount) {
/* 15 */     this.p = AbstractDungeon.player;
/* 16 */     setValues(this.p, AbstractDungeon.player, amount);
/* 17 */     this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
/*    */   }
/*    */   
/*    */   public void update()
/*    */   {
/* 22 */     if (this.p.hand.size() >= 10) {
/* 23 */       this.isDone = true; return;
/*    */     }
/*    */     AbstractCard card;
/* 26 */     if (this.p.discardPile.size() == 1) {
/* 27 */       card = (AbstractCard)this.p.discardPile.group.get(0);
/* 28 */       this.p.hand.addToHand(card);
/* 29 */       card.lighten(false);
			   card.upgrade();
/* 30 */       this.p.discardPile.removeCard(card);
/* 31 */       this.p.hand.refreshHandLayout();
/* 32 */       this.isDone = true;
/* 33 */       return;
/*    */     }
/*    */     
/* 36 */     if (this.duration == 0.5F) {
/* 37 */       AbstractDungeon.gridSelectScreen.open(this.p.discardPile, this.amount, "return and upgrade", false);
/* 38 */       tickDuration();
/* 39 */       return;
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 44 */     if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
/* 45 */       for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
/* 46 */         this.p.hand.addToHand(c);
				 c.upgrade();
/* 47 */         this.p.discardPile.removeCard(c);
/* 48 */         c.lighten(false);
/* 49 */         c.unhover();
/*    */       }
/* 51 */       AbstractDungeon.gridSelectScreen.selectedCards.clear();
/* 52 */       this.p.hand.refreshHandLayout();
/*    */       
/* 54 */       for (AbstractCard c : this.p.discardPile.group) {
/* 55 */         c.unhover();
/* 56 */         c.target_x = CardGroup.DISCARD_PILE_X;
/* 57 */         c.target_y = 0.0F;
/*    */       }
/* 59 */       this.isDone = true;
/*    */     }
/*    */     
/* 62 */     tickDuration();
/*    */   }
/*    */ }