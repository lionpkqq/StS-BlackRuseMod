/*     */ package blackrusemod.vfx;
/*     */ 
/*     */ import com.badlogic.gdx.Gdx;
/*     */ import com.badlogic.gdx.graphics.Color;
/*     */ import com.badlogic.gdx.graphics.g2d.SpriteBatch;
		  import com.badlogic.gdx.graphics.g2d.TextureAtlas;
/*     */ import com.badlogic.gdx.math.Interpolation;
/*     */ import com.badlogic.gdx.math.MathUtils;
/*     */ import com.megacrit.cardcrawl.core.CardCrawlGame;
/*     */ import com.megacrit.cardcrawl.core.Settings;
/*     */ import com.megacrit.cardcrawl.helpers.ImageMaster;
/*     */ import com.megacrit.cardcrawl.vfx.GenericSmokeEffect;
/*     */ 
/*     */ public class FinishingImpactEffect extends com.megacrit.cardcrawl.vfx.AbstractGameEffect
/*     */ {
/*     */   private float x;
/*     */   private float y;
/*     */   private static final float DUR = 0.6F;
/*     */   private TextureAtlas.AtlasRegion img;
/*  21 */   private boolean playedSound = false;
/*     */   
/*     */   public FinishingImpactEffect(float x, float y) {
/*  24 */     this.img = ImageMaster.VERTICAL_IMPACT;
/*  25 */     this.x = (x - this.img.packedWidth / 2.0F);
/*  26 */     this.y = (y - this.img.packedHeight * 0.01F);
/*  27 */     this.startingDuration = 0.6F;
/*  28 */     this.duration = DUR;
/*  29 */     this.scale = Settings.scale;
/*  30 */     this.rotation = MathUtils.random(40.0F, 50.0F);
/*  31 */     this.color = new Color(0.0F, 1.0F, 1.0F, 1.0F);
/*  32 */     this.renderBehind = false;
/*  33 */     for (int i = 0; i < 50; i++) {
/*  34 */       com.megacrit.cardcrawl.dungeons.AbstractDungeon.effectsQueue.add(new GenericSmokeEffect(x + 
/*  35 */         MathUtils.random(-280.0F, 250.0F) * Settings.scale, y - 80.0F * Settings.scale));
/*     */     }
/*     */   }
/*     */   
/*     */   private void playRandomSfX() {
/*  40 */     CardCrawlGame.sound.playA("BLUNT_HEAVY", -0.3F);
/*     */   }
/*     */   
/*     */   public void update() {
/*  44 */     this.duration -= Gdx.graphics.getDeltaTime();
/*  45 */     if (this.duration < 0.0F) {
/*  46 */       this.isDone = true;
/*     */     }
/*  48 */     if ((this.duration < 0.5F) && 
/*  49 */       (!this.playedSound)) {
/*  50 */       playRandomSfX();
/*  51 */       this.playedSound = true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  56 */     if (this.duration > 0.2F) {
/*  57 */       this.color.a = Interpolation.fade.apply(0.5F, 0.0F, (this.duration - 0.34F) * 5.0F);
/*     */     } else {
/*  59 */       this.color.a = Interpolation.fade.apply(0.0F, 0.5F, this.duration * 5.0F);
/*     */     }
/*  61 */     this.scale = Interpolation.fade.apply(Settings.scale * 1.1F, Settings.scale * 1.05F, this.duration / 0.6F);
/*     */   }
/*     */   
/*     */   public void render(SpriteBatch sb)
/*     */   {
/*  66 */     sb.setColor(this.color);
/*  67 */     sb.setBlendFunction(770, 1);
/*  68 */     sb.draw(this.img, this.x + 
/*     */     
/*  70 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.3F, this.scale * 0.8F, this.rotation - 18.0F);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  79 */     sb.draw(this.img, this.x + 
/*     */     
/*  81 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.3F, this.scale * 0.8F, this.rotation + 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  89 */       MathUtils.random(12.0F, 18.0F));
/*  90 */     sb.draw(this.img, this.x + 
/*     */     
/*  92 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.4F, this.scale * 0.5F, this.rotation - 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */       MathUtils.random(-10.0F, 14.0F));
/* 101 */     sb.draw(this.img, this.x + 
/*     */     
/* 103 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.7F, this.scale * 0.9F, this.rotation + 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 111 */       MathUtils.random(20.0F, 28.0F));
/* 112 */     sb.draw(this.img, this.x + 
/*     */     
/* 114 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 1.5F, this.scale * 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 121 */       MathUtils.random(1.4F, 1.6F), this.rotation);
/*     */     
/* 123 */     Color c = Color.GOLD.cpy();
/* 124 */     c.a = this.color.a;
/* 125 */     sb.setColor(c);
/* 126 */     sb.draw(this.img, this.x + 
/*     */     
/* 128 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 135 */       MathUtils.random(0.8F, 1.2F), this.rotation);
/*     */     
/* 137 */     sb.draw(this.img, this.x + 
/*     */     
/* 139 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale, this.scale * 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 146 */       MathUtils.random(0.4F, 0.6F), this.rotation);
/*     */     
/* 148 */     sb.draw(this.img, this.x + 
/*     */     
/* 150 */       MathUtils.random(-10.0F, 10.0F) * Settings.scale, this.y, this.img.packedWidth / 2.0F, 0.0F, this.img.packedWidth, this.img.packedHeight, this.scale * 0.5F, this.scale * 0.7F, this.rotation + 
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 158 */       MathUtils.random(20.0F, 28.0F));
/* 159 */     sb.setBlendFunction(770, 771);
/*     */   }
/*     */
/* 162 */   public void dispose() {
/* 163 */       // Stub required in 2.0 - if this is needed, put stuff here.
/*     */   }
/*     */ }


/* Location:              C:\Users\MoQian\Downloads\jd-gui-windows-1.4.0\jd-gui-windows-1.4.0\desktop-1.0.jar!\com\megacrit\cardcrawl\vfx\combat\VerticalImpactEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */