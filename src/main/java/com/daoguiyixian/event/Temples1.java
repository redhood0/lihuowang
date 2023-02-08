package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.ImageEventPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.cards.DaLiDan;
import com.daoguiyixian.cards.ManTouEat;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.relics.XuanyangYupei;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.function.Consumer;

public class Temples1 extends PhasedEvent {

    public static final String ID = ModHelper.MakePath("Temples1");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public AbstractRelic relicMetric = null;
    public Temples1() {
        super(ID, title, "lihuowangResources/img/event/Temples1.png");

        //This is where you define all the event's phases.
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(OPTIONS[0], (i)->transitionKey("temples1"))
                .addOption(OPTIONS[1], (i)->transitionKey("dog1"))
        );

        //-------------------------------------------------------
        registerPhase("temples1", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[2],(i)->transitionKey("temples2")));


        registerPhase("temples2", new CombatPhase(MonsterHelper.GREMLIN_GANG_ENC)
                .addRewards(false, new Consumer<AbstractRoom>() {
                    @Override
                    public void accept(AbstractRoom room) {
//                        room.addCardReward(rewardItem);
                        room.addCardToRewards();
                        room.addGoldToRewards(MathUtils.random(20,30));
                    }
                }).setNextKey("temples3"));

        registerPhase("temples3", new TextPhase(DESCRIPTIONS[4])
                .addOption(OPTIONS[4],(i)->openMap()));
        //-------------------------------------------------------

        registerPhase("dog1", new TextPhase(DESCRIPTIONS[3])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {

                        AbstractCard mantou = new ManTouEat();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(mantou, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("dog2");

                    }
                }));

        registerPhase("dog2", new TextPhase(DESCRIPTIONS[5])
                .addOption(OPTIONS[4],(i)->openMap()));
        //-------------------------------------------------------


        transitionKey("Start");
    }
}
