package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.cards.Daqianlu;
import com.daoguiyixian.cards.HuoWoZhenJin;
import com.daoguiyixian.cards.MaJiang;
import com.daoguiyixian.helpers.ModHelper;
import com.daoguiyixian.relics.PianJing;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Pianjing2 extends PhasedEvent {


    public static final String ID = ModHelper.MakePath("Pianjing2");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;

    public AbstractRelic relicMetric = null;
    public Pianjing2() {
        super(ID, title, "lihuowangResources/img/event/pianjing_event.png");

        //This is where you define all the event's phases.
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(OPTIONS[0], (i)->transitionKey("money1"))
                .addOption(OPTIONS[1], (i)->transitionKey("pianjing1"))
                .addOption(OPTIONS[2], (i)->transitionKey("damaj1"))
        );

        //-------------------------------------------------------
        registerPhase("money1", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        int goldAmount = AbstractDungeon.miscRng.random(50, 80);
                        AbstractDungeon.effectList.add(new RainingGoldEffect(goldAmount));
                        AbstractDungeon.player.gainGold(goldAmount);
                        AbstractEvent.logMetricGainGold("Golden Wing", "Gained Gold", goldAmount);

                        AbstractRelic relicMetric = RelicLibrary.getRelic("Pear").makeCopy();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), relicMetric);

//                        AbstractCard huoWoZhenJin = new HuoWoZhenJin();
//                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(huoWoZhenJin, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("money2");
                    }
                }));


        registerPhase("money2", new TextPhase(DESCRIPTIONS[4])
                .addOption(OPTIONS[6],(i)->openMap()));
        //-------------------------------------------------------


        //-------------------------------------------------------

        registerPhase("pianjing1", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[4], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        AbstractRelic relicMetric = RelicLibrary.getRelic(PianJing.ID).makeCopy();
                        AbstractDungeon.getCurrRoom().spawnRelicAndObtain((float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2), relicMetric);
//                        AbstractCard huoWoZhenJin = new HuoWoZhenJin();
//                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(huoWoZhenJin, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("pianjing2");
                    }
                }));

        registerPhase("pianjing2", new TextPhase(DESCRIPTIONS[4])
                .addOption(OPTIONS[6],(i)->openMap()));
        //-------------------------------------------------------

        //-------------------------------------------------------

        RewardItem rewardItem = new RewardItem();
//        rewardItem.bonusGold = MathUtils.random(50,60);

//        rewardItem.type= RewardItem.RewardType.;
        ArrayList<AbstractCard> card = new ArrayList<>();
        card.add(new MaJiang());
        rewardItem.cards = card;

        registerPhase("damaj1", new CombatPhase(MonsterHelper.TWO_THIEVES_ENC)
                .addRewards(false, new Consumer<AbstractRoom>() {
                    @Override
                    public void accept(AbstractRoom room) {
//                        room.addCardReward(rewardItem);
                        room.addCardReward(rewardItem);
                        room.addGoldToRewards(MathUtils.random(30,40));
                    }
                }).setNextKey("damaj2"));

        registerPhase("damaj2", new TextPhase(DESCRIPTIONS[3])
                .addOption(OPTIONS[6],(i)->openMap()));
        //-------------------------------------------------------


        transitionKey("Start");
    }
}
