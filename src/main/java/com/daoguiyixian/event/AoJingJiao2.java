package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.cards.HuoWoZhenJin;
import com.daoguiyixian.cards.ManTouEat;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.function.Consumer;

public class AoJingJiao2 extends PhasedEvent {

    public static final String ID = ModHelper.MakePath("AoJingJiao2");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public AbstractRelic relicMetric = null;
    public AoJingJiao2() {
        super(ID, title, "lihuowangResources/img/event/AoJingJiao2.png");

        //This is where you define all the event's phases.
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(OPTIONS[0], (i)->transitionKey("daqianlu1"))
                .addOption(OPTIONS[1], (i)->transitionKey("huowo1"))
        );

        //-------------------------------------------------------
        registerPhase("daqianlu1", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[4],(i)->openMap()));
        //-------------------------------------------------------


        //-------------------------------------------------------

        registerPhase("huowo1", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {

                        AbstractCard huoWoZhenJin = new HuoWoZhenJin();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(huoWoZhenJin, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("huowo2");

                    }
                }));

        registerPhase("huowo2", new TextPhase(DESCRIPTIONS[3])
                .addOption(OPTIONS[4],(i)->openMap()));
        //-------------------------------------------------------


        transitionKey("Start");
    }
}
