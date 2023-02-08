package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.cards.AnDing;
import com.daoguiyixian.cards.BlackTaiSui;
import com.daoguiyixian.cards.Lisui_dog;
import com.daoguiyixian.cards.ManTouEat;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Tianzai2 extends PhasedEvent {
    public static final String ID = ModHelper.MakePath("Tianzai2");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public AbstractRelic relicMetric = null;

    //判断方法
    public boolean haveB_M(){
        CardGroup cardGroup =  AbstractDungeon.player.masterDeck;
        boolean f1 = false;
        boolean f2 = false;
        boolean f3 = false;

        for(AbstractCard c : cardGroup.group ){
            if(c.cardID == BlackTaiSui.ID){
                f1 = true;
            }
            if( c.cardID == ManTouEat.ID){
                f2 = true;
            }
        }
        if(f1 && f2){
            f3 = true;
        }
        return f3;
    }
    public Tianzai2() {
        super(ID, title, "lihuowangResources/img/event/tianzai2.png");

        //This is where you define all the event's phases.
        // AbstractDungeon.player.masterDeck::hasUpgradableCards
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(new TextPhase.OptionInfo(haveB_M() ? OPTIONS[0] : OPTIONS[1])
                        .enabledCondition(new Supplier<Boolean>() {
                            @Override
                            public Boolean get() {
                                return haveB_M();
                            }
                        }),new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        AbstractCard blackTaiSui =  AbstractDungeon.player.masterDeck.findCardById(BlackTaiSui.ID);
                        AbstractCard manTouEat =  AbstractDungeon.player.masterDeck.findCardById(ManTouEat.ID);
                        //移除卡
                        AbstractDungeon.effectList.add(new PurgeCardEffect(blackTaiSui));
                        AbstractDungeon.player.masterDeck.removeCard(blackTaiSui);
                        logMetricCardRemoval("天灾", "Removed blackTaiSui", blackTaiSui);
                        AbstractDungeon.effectList.add(new PurgeCardEffect(manTouEat));
                        AbstractDungeon.player.masterDeck.removeCard(manTouEat);
                        logMetricCardRemoval("天灾", "Removed manTouEat", manTouEat);


                        transitionKey("lisui2");
                    }
                })
                .addOption(OPTIONS[2], (i)->transitionKey("battle1"))
        );



        //-------------------------------------------------------
        registerPhase("lisui2", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        //获得卡
                        AbstractCard lisui = new Lisui_dog();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(lisui, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));

                        transitionKey("lisui3");
                    }
                }));




        registerPhase("lisui3", new TextPhase(DESCRIPTIONS[3])
                .addOption(OPTIONS[7],(i)->openMap()));
        //-------------------------------------------------------
        registerPhase("battle1", new CombatPhase(MonsterHelper.GREMLIN_LEADER_ENC)
                .addRewards(false, new Consumer<AbstractRoom>() {
                    @Override
                    public void accept(AbstractRoom room) {
//                        room.addCardReward(rewardItem);
                        room.addCardToRewards();
                        room.addRelicToRewards(AbstractRelic.RelicTier.UNCOMMON);
                        room.addGoldToRewards(MathUtils.random(20,40));
                    }
                }).setNextKey("battle2"));

        registerPhase("battle2", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[7],(i)->openMap()));

        //-------------------------------------------------------


        transitionKey("Start");
    }
}
