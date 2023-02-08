package com.daoguiyixian.event;

import basemod.abstracts.events.PhasedEvent;
import basemod.abstracts.events.phases.CombatPhase;
import basemod.abstracts.events.phases.TextPhase;
import com.badlogic.gdx.math.MathUtils;
import com.daoguiyixian.cards.AnDing;
import com.daoguiyixian.cards.Daqianlu;
import com.daoguiyixian.helpers.ModHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.helpers.MonsterHelper;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import java.util.ArrayList;
import java.util.function.Consumer;

public class HospitalStage1 extends PhasedEvent {
    public static final String ID = ModHelper.MakePath("HospitalStage1");
    //These eventStrings should be defined in a json file and loaded in your main mod file. See https://github.com/daviscook477/BaseMod/wiki/Custom-Strings
    private static final EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(ID);
    private static final String[] DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    private static final String[] OPTIONS = eventStrings.OPTIONS;
    private static final String title = eventStrings.NAME;


    public HospitalStage1() {
        super(ID, title, "lihuowangResources/img/event/HospitalStage1.png");

        //This is where you define all the event's phases.
        registerPhase("Start", new TextPhase(DESCRIPTIONS[0])
                .addOption(OPTIONS[0], (i)->transitionKey("Phase 2"))
                .addOption(OPTIONS[1], (i)->transitionKey("Phase 2"))
                .addOption(OPTIONS[2], (i)->transitionKey("Phase 2"))
        );
        //This example registers a TextPhase, which is the standard event screen with some text and buttons you can click.
        //The event's text should be from DESCRIPTIONS and OPTIONS. For this example, it will have two buttons labeled "Option 1" and "Option 2", which will take you to Phase 2 and Phase 3 respectively.

//(i)->openMap()
//        registerPhase("Phase 2", new TextPhase(DESCRIPTIONS[1]).addOption(OPTIONS[3], new Consumer<Integer>() {
//            @Override
//            public void accept(Integer i) {
//                //玩家金钱减少
//                AbstractDungeon.player.gainGold(100);
//                openMap();
//            }
//        }));
        //This is another TextPhase with only one option, which will open the map, which ends the event.

//        registerPhase("Phase 3", new CombatPhase(MonsterHelper.CULTIST_ENC)
//                .addRewards(true, (room)->room.addRelicToRewards(AbstractRelic.RelicTier.RARE))
//                .setNextKey("Phase 2"));

//        RewardItem rewardItem = new RewardItem();
//        rewardItem.type= RewardItem.RewardType.CARD;
//        ArrayList<AbstractCard> card = new ArrayList<>();
//        card.add(new AnDing());
//        rewardItem.cards = card;
        registerPhase("Phase 2", new TextPhase(DESCRIPTIONS[1])
                .addOption(OPTIONS[3], new Consumer<Integer>() {
                    @Override
                    public void accept(Integer i) {
                        //镇定剂加入玩家卡组
//                        AbstractDungeon.player.masterDe

                        AbstractCard anding = new AnDing();
                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(anding, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                        transitionKey("Phase 3");
//                        openMap();
                    }
                }));

        registerPhase("Phase 3", new TextPhase(DESCRIPTIONS[2])
                .addOption(OPTIONS[4],(i)->openMap()));
//                .addRewards(false, (room) -> room.addCardReward(rewardItem))
//                .addRewards(true, (room)->room.addRelicToRewards(AbstractRelic.RelicTier.RARE))
//                .setNextKey("Phase 4"));
        //This will cause the player to enter combat when Phase 3 starts.
        //The combat will have a card reward and a random rare relic reward.
        //After combat ends, it will transition to Phase 2.
        //CombatPhase has many customization options. Look at the class for details.

        //This sets the starting point of the event.
        transitionKey("Start");
    }

//    一般来说：当一个阶段结束时，它应该调用transitionKey移动到另一个阶段，或者openMap结束事件。如果两者都没有发生，您的事件将导致软锁。
}
