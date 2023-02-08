package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.action.BlackTaiSuiAction;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RupturePower;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class WangLiangBodyPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath("WangLiangBodyPower");


    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int count = 0;
    private boolean isUpgread = false;
    public WangLiangBodyPower(AbstractCreature owner, int drawAmount,boolean isUpgread) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
//        this.amount = drawAmount;
        this.type = PowerType.BUFF;
        this.amount = 1;
        this.isUpgread = isUpgread;
//        this.loadRegion("tools");
        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/wangliang84.png";
        String path48 = "lihuowangResources/img/power/wangliang32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
//        this.updateDescription();
        this.description =DESCRIPTIONS[0];
        this.priority = 24;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[1];
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
//        }

    }

    public void atStartOfTurnPostDraw() {
//        this.flash();
////        this.addToBot(new DrawCardAction(this.owner, 1));
////        DamageInfo.DamageType.NORMAL
//        for(int i = 0;i < this.amount;i++){
//            int[] A =  {2,2};
//            this.addToBot(new BlackTaiSuiAction(this.owner,  new DamageInfo((AbstractCreature)this.owner,2),A));
//        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
       AbstractPlayer p = AbstractDungeon.player ;
        if(card.type == AbstractCard.CardType.CURSE){
            count++;
        }
        if(count%2==0 && count != 0 ){
            if(this.isUpgread){
                this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 2*this.amount), 2*this.amount));
                count=0;
                return;
            }else {
                this.addToTop(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 1*this.amount), 1*this.amount));
                count=0;
                return;
            }

            //加攻
        }
    }
}
