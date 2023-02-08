package com.daoguiyixian.power;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.daoguiyixian.action.BlackTaiSuiAction;
import com.daoguiyixian.characters.Lihuowang_Character;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class HuoWoPower extends AbstractPower {
    public static final String POWER_ID = ModHelper.MakePath("HuoWoPower");


    // 能力的本地化字段
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    // 能力的名称
    private static final String NAME = powerStrings.NAME;
    // 能力的描述
    private static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static int num;

    public HuoWoPower(AbstractCreature owner, int drawAmount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;
//        this.amount = drawAmount;
        this.amount = 1;
        num = drawAmount;

//        this.loadRegion("tools");
        // 添加一大一小两张能力图
        String path128 = "lihuowangResources/img/power/huowo84.png";
        String path48 = "lihuowangResources/img/power/huowo32.png";
        this.region128 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path128), 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(ImageMaster.loadImage(path48), 0, 0, 32, 32);

        // 首次添加能力更新描述
        this.updateDescription();
//        this.priority = 25;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
        HUOWO_COUNT = 0;
//        if (this.amount == 1) {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
//        } else {
//            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[3] + this.amount + DESCRIPTIONS[4];
//        }

    }




    public static int MAX = 2;
    public static int HUOWO_COUNT = 0;
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        HUOWO_COUNT = 0;
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        LihuowangMod.logger.error("=======================>");
        LihuowangMod.logger.error("========>"+card.cardID);
        LihuowangMod.logger.error("=======================>");
//        count++;
//        if(count){
//
//        }

        if (card.type == AbstractCard.CardType.STATUS && card.cardID.equals("Burn")) {
//            AbstractDungeon.player.getRelic("Medical Kit").flash();
            AbstractDungeon.player.getPower( ModHelper.MakePath("HuoWoPower")).flash();
            card.exhaust = true;
            action.exhaustCard = true;
            ++HUOWO_COUNT;
            //回血
            AbstractDungeon.player.heal(4);
        }
    }

//    public void atStartOfTurnPostDraw() {
//        this.flash();
////        this.addToBot(new DrawCardAction(this.owner, 1));
////        DamageInfo.DamageType.NORMAL
//        for(int i = 0;i < this.amount;i++){
//            int[] A =  {2,2};
//            this.addToBot(new BlackTaiSuiAction(this.owner,  new DamageInfo((AbstractCreature)this.owner,2),A));
//        }
//    }

}
