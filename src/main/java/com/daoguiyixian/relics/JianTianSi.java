package com.daoguiyixian.relics;

import basemod.abstracts.CustomRelic;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.modcore.LihuowangMod;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.Iterator;

public class JianTianSi extends CustomRelic {
    // 遗物ID
    public static final String ID = ModHelper.MakePath("JianTianSi");
    // 图片路径
    private static final String IMG_PATH = "lihuowangResources/img/relics/jiantiansi.png";
    private static final String OUTLINE = "lihuowangResources/img/relics/outline/jiantiansi.png";

    // 遗物类型
    private static final RelicTier RELIC_TIER = RelicTier.UNCOMMON;
    // 点击音效
    private static final LandingSound LANDING_SOUND = LandingSound.FLAT;

    public JianTianSi() {

        super(ID, ImageMaster.loadImage(IMG_PATH),
                ImageMaster.loadImage(OUTLINE),
                RELIC_TIER,
                LANDING_SOUND);

//        tips.clear();
//        tips.add(new PowerTip(name, description));
    }

    // 获取遗物描述，但原版游戏只在初始化和获取遗物时调用，故该方法等于初始描述
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }




    @Override
    public void atBattleStart() {
        super.atBattleStart();
//        this.counter=0;
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
    }

    @Override
    public void atBattleStartPreDraw() {
////        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        this.addToBot(new MakeTempCardInDrawPileAction(new Burn(), 3, true,true));
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
//        int money = 0;
//        //给钱
//        switch (m.type){
//            case BOSS:
//                money=50;
//                break;
//
//            case NORMAL:
//                money=5;
//                break;
//
//            case ELITE:
//                money=30;
//                break;
//        }
//        LihuowangMod.logger.error("================m.isDead=================>" + m.name + "===="+m.);
//
//        this.flash();
//        AbstractDungeon.player.gainGold(money);

    }
   private boolean isElite;
    private boolean isBoss;

//    public void beforeEnergyPrep() {
//
//
//    }

//    @Override
//    public void onEnterRoom(AbstractRoom room) {
//
//    }

    @Override
    public void atPreBattle() {

        isElite = AbstractDungeon.getCurrRoom().eliteTrigger;


        Iterator var2 = AbstractDungeon.getMonsters().monsters.iterator();

        while(var2.hasNext()) {
            AbstractMonster m = (AbstractMonster)var2.next();
            if (m.type == AbstractMonster.EnemyType.BOSS) {
                isBoss = true;
            }
        }
    }

    //    public void atBattleStart() {
//        if (AbstractDungeon.getCurrRoom().eliteTrigger) {
//            this.flash();
//            Iterator var1 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
//
//            while(var1.hasNext()) {
//                AbstractMonster m = (AbstractMonster)var1.next();
//                if (m.currentHealth > (int)((float)m.maxHealth * (1.0F - this.MODIFIER_AMT))) {
//                    m.currentHealth = (int)((float)m.maxHealth * (1.0F - this.MODIFIER_AMT));
//                    m.healthBarUpdatedEvent();
//                }
//            }
//
//            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        }
//
//    }


    @Override
    public void onVictory() {
        this.flash();
        this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
//        AbstractRoom.RoomType.BOSS

        int money = 0;
        //给钱
        if(isBoss){
            money=50;
        }else if (isElite){
            money=40;
        }else {
            money=10;
        }

        AbstractPlayer p = AbstractDungeon.player;
        if (p.currentHealth > 0) {
            p.gainGold(money);
        }
        LihuowangMod.logger.error("=======money==========>"+money);

    }

    public AbstractRelic makeCopy() {
        return new JianTianSi();
    }

}
