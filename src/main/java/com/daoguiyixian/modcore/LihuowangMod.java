package com.daoguiyixian.modcore;

import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.daoguiyixian.cards.Bite2;
import com.daoguiyixian.cards.Defend;
import com.daoguiyixian.cards.Strike;
import com.daoguiyixian.characters.Lihuowang_Character;
import com.daoguiyixian.event.*;
import com.daoguiyixian.relics.*;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.Exordium;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.localization.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;

import static com.daoguiyixian.characters.Lihuowang_Character.Enums.EXAMPLE_CARD;
import static com.daoguiyixian.characters.Lihuowang_Character.Enums.MY_CHARACTER;
import static com.megacrit.cardcrawl.core.Settings.language;


@SpireInitializer
public class LihuowangMod implements EditCardsSubscriber, EditStringsSubscriber, EditCharactersSubscriber, EditRelicsSubscriber, EditKeywordsSubscriber, PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(Lihuowang_Character.class.getName());
    // 人物选择界面按钮的图片
    private static final String MY_CHARACTER_BUTTON = "lihuowangResources/img/char/Character_Button.png";
    // 人物选择界面的立绘
    private static final String MY_CHARACTER_PORTRAIT = "lihuowangResources/img/char/Character_Portrait.png";
    // 攻击牌的背景（小尺寸）
    private static final String BG_ATTACK_512 = "lihuowangResources/img/512/bg_attack_512.png";
    // 能力牌的背景（小尺寸）
    private static final String BG_POWER_512 = "lihuowangResources/img/512/bg_power_512.png";
    // 技能牌的背景（小尺寸）
    private static final String BG_SKILL_512 = "lihuowangResources/img/512/bg_skill_512.png";
    // 在卡牌和遗物描述中的能量图标
    private static final String SMALL_ORB = "lihuowangResources/img/char/small_orb.png";
    // 攻击牌的背景（大尺寸）
    private static final String BG_ATTACK_1024 = "lihuowangResources/img/1024/bg_attack.png";
    // 能力牌的背景（大尺寸）
    private static final String BG_POWER_1024 = "lihuowangResources/img/1024/bg_power.png";
    // 技能牌的背景（大尺寸）
    private static final String BG_SKILL_1024 = "lihuowangResources/img/1024/bg_skill.png";
    // 在卡牌预览界面的能量图标
    private static final String BIG_ORB = "lihuowangResources/img/char/card_orb.png";
    // 小尺寸的能量图标（战斗中，牌堆预览）
    private static final String ENEYGY_ORB = "lihuowangResources/img/char/cost_orb.png";

    // 除以255得出需要的参数。你也可以直接写出计算值。
    public static final Color BLOOD_COLOR = new Color(249.0F / 255.0F, 1.0F / 255.0F, 5.0F / 255.0F, 1.0F);

    // 构造方法
    public LihuowangMod() {
        BaseMod.subscribe(this);
        BaseMod.addColor(EXAMPLE_CARD, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BLOOD_COLOR, BG_ATTACK_512, BG_SKILL_512, BG_POWER_512, ENEYGY_ORB, BG_ATTACK_1024, BG_SKILL_1024, BG_POWER_1024, BIG_ORB, SMALL_ORB);

    }

    // 注解需要调用的方法，必须写
    public static void initialize() {
        new LihuowangMod();
    }

    @Override
    public void receiveEditCards() {
        // TODO 这里写添加你卡牌的代码
        new AutoAdd("lihuowang")
                .packageFilter("com.daoguiyixian.cards")
                .setDefaultSeen(true)
                .cards();

//        BaseMod.addCard(new Strike());
//        BaseMod.addCard(new Defend());
//        BaseMod.addCard(new Bite2());


    }

    @Override
    public void receiveEditStrings() {
        String lang;
        if (language == Settings.GameLanguage.ZHS) {
            lang = "ZHS"; // 如果语言设置为简体中文，则加载ZHS文件夹的资源
        } else {
            lang = "ENG"; // 如果没有相应语言的版本，默认加载英语
        }
        BaseMod.loadCustomStringsFile(CardStrings.class, "lihuowangResources/localization/" + lang + "/cards.json"); // 加载相应语言的卡牌本地化内容。
        // 如果是中文，加载的就是"ExampleResources/localization/ZHS/cards.json"

        // 这里添加注册本地化文本
        BaseMod.loadCustomStringsFile(CharacterStrings.class, "lihuowangResources/localization/" + lang + "/characters.json");
        BaseMod.loadCustomStringsFile(RelicStrings.class, "lihuowangResources/localization/" + lang + "/relics.json");
        BaseMod.loadCustomStringsFile(PowerStrings.class, "lihuowangResources/localization/" + lang + "/powers.json");
        BaseMod.loadCustomStringsFile(EventStrings.class, "lihuowangResources/localization/" + lang + "/events.json");

    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new Lihuowang_Character(CardCrawlGame.playerName), MY_CHARACTER_BUTTON, MY_CHARACTER_PORTRAIT, MY_CHARACTER);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new Xinsu(), RelicType.RED);
        BaseMod.addRelic(new Dengjie(), RelicType.RED);
        BaseMod.addRelic(new XinZHuoJian(), RelicType.SHARED);
        BaseMod.addRelic(new HuoLian(), RelicType.SHARED);
        BaseMod.addRelic(new XinzhuoFangchui(), RelicType.SHARED);
        BaseMod.addRelic(new JianTianSi(), RelicType.SHARED);
        BaseMod.addRelic(new XuanyangYupei(), RelicType.SHARED);
        BaseMod.addRelic(new PianJing(), RelicType.SHARED);
        BaseMod.addRelic(new Renpi(), RelicType.RED);

//        BaseMod.addRelic(new Huowozhenjing(),RelicType.SHARED);火沃真经遗物版

//        BaseMod.addRelicToCustomPool(new Xinsu(), EXAMPLE_CARD); // RelicType表示是所有角色都能拿到的遗物，还是一个角色的独有遗物
    }

    @Override
    public void receiveEditKeywords() {
//        BaseMod.addKeyword("lihuowang", "癫狂", new String[] {"癫狂"}, "拥有 #y癫狂 的角色造成伤害增加50%，受到伤害减少50%");
        Gson gson = new Gson();
        String lang = "eng";
        if (language == Settings.GameLanguage.ZHS) {
            lang = "zhs";
        }

        String json = Gdx.files.internal("lihuowangResources/localization/ZHS/keywords_" + lang + ".json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword("lihuowang", keyword.NAMES[0], keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }


    @Override
    public void receivePostInitialize() {

        BaseMod.addEvent(HospitalStage1.ID, HospitalStage1.class, Exordium.ID);
        BaseMod.addEvent(River4Daqianlu1.ID, River4Daqianlu1.class, Exordium.ID);
        BaseMod.addEvent(Cave4DaliDan1.ID, Cave4DaliDan1.class, Exordium.ID);
        BaseMod.addEvent(Temples1.ID, Temples1.class, Exordium.ID);
        BaseMod.addEvent(River4Daqianlu1.ID, River4Daqianlu1.class, Exordium.ID);
        BaseMod.addEvent(Temples1.ID, Temples1.class, Exordium.ID);

        BaseMod.addEvent(AoJingJiao2.ID, AoJingJiao2.class, TheCity.ID);
        BaseMod.addEvent(Tianzai2.ID, Tianzai2.class, TheCity.ID);

        BaseMod.addEvent(AoJingJiao2.ID, AoJingJiao2.class, TheCity.ID);
        BaseMod.addEvent(Pianjing2.ID, Pianjing2.class, TheCity.ID);
        BaseMod.addEvent(Tianzai2.ID, Tianzai2.class, TheCity.ID);
        BaseMod.addEvent(Tianzai2.ID, Tianzai2.class, TheCity.ID);
        //Exordium;TheCity.ID;TheBeyond.ID
    }
}
