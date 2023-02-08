package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.TextPhase;
import com.daoguiyixian.cards.AnDing;
import com.daoguiyixian.cards.DaLiDan;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.relics.XuanyangYupei;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.function.Consumer;

public class Cave4DaliDan1 extends PhasedEvent {
    public static final String ID = ModHelper.MakePath("Cave4DaliDan1");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public AbstractRelic relicMetric = null;
    public Cave4DaliDan1() {
        super(ID, title, "lihuowangResources/img/event/Cave4DaliDan1.png");

        //This is where you define all the event's phases.
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(OPTIONS[0], (i)->transitionKey("yupei1"))
                .addOption(OPTIONS[1], (i)->transitionKey("dali1"))
        );



        registerPhase("yupei1",new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[2], new Consumer<Integer>() {

                    @Override
                    public void accept(Integer i) {
                        relicMetric = RelicLibrary.getRelic(XuanyangYupei.ID).makeCopy();

                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), relicMetric);
                        transitionKey("dali2");
                    }
                }));

        registerPhase("dali1", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {

                        AbstractCard dali = new DaLiDan();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(dali, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("dali2");

                    }
                }));


        registerPhase("dali2", new TextPhase(DESCRIPTIONS[3])
                .addOption(OPTIONS[4],(i)->openMap()));

        transitionKey("Start");
    }

}
