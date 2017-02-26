package com.mygdx.game.Web;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GlobalClasses.Assets;
import com.mygdx.game.MyBaseClasses.HttpCommand;
import com.mygdx.game.MyBaseClasses.HttpErrors;
import com.mygdx.game.MyBaseClasses.MyButton;
import com.mygdx.game.MyBaseClasses.MyLabel;
import com.mygdx.game.MyBaseClasses.MyStage;
import com.mygdx.game.MyBaseClasses.MyTimerActor;
import com.mygdx.game.MyGdxGame;

/**
 * Created by tanulo on 2017. 02. 22..
 */
public class BattleListStage extends MyStage {


private MyTimerActor myTimerActor;

    private HttpCommand httpCommand = null;
    private Preferences pref_user_pw;

    private static final String reallyLongString = "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n"
            + "This\nIs\nA\nReally\nLong\nString\nThat\nHas\nLots\nOf\nLines\nAnd\nRepeats.\n";


    public BattleListStage(Viewport viewport, Batch batch, MyGdxGame game) {
        super(viewport, batch, game);
        pref_user_pw = Gdx.app.getPreferences(ConnectionScreen.PREFS);
        httpCommand = new HttpCommand("http://193.224.143.135:9999"){
            @Override
            protected void failed(HttpErrors httpErrors) {

            }

            @Override
            protected void responsed() {

            }
        };
        httpCommand.getSend().put("user",pref_user_pw.getString("user"));
        httpCommand.getSend().put("password",pref_user_pw.getString("password"));
        httpCommand.getSend().put("message", String.valueOf(MessageTypes.HELLO.value));


        addActor(myTimerActor = new MyTimerActor() {

            @Override
            public void init() {
                super.init();
            }

            @Override
            public void tick() {
                httpCommand.sendCommand();
            }
        });
        myTimerActor.start();
        myTimerActor.setInterval(4);

        final MyLabel label = new MyLabel("", game.getLabelStyle());
        label.setPosition(700,500);

        final MyButton button = new MyButton("Play", game.getTextButtonStyle(140));
        button.setSize(300,160);
        button.setPosition(700,300);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        final List list = new List(game.getListStyle());
        list.getItems().add("asd");
        list.getItems().add("123");
        list.getItems().add("asdfgsd");
        list.getItems().add("cvbvxckbmlcv");
        list.getItems().add("aesd");
        list.getItems().add("12xd3");
        list.getItems().add("asxsdfgsd");
        list.getItems().add("cdvdbvxckbmlcv");
        list.getItems().add("axcsd");
        list.getItems().add("1sc23");
        list.getItems().add("asvsdfgsd");
        list.getItems().add("cvxbvxckbmlcv");
        list.getItems().add("asyxd");
        list.getItems().add("12d3");
        list.getItems().add("ascddfgsd");
        list.getItems().add("cvvbavxckbmlcv");
        list.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println((String)list.getSelected());
                label.setText((String)list.getSelected());
                if (button.getStage()==null){
                    BattleListStage.this.addActor(button);
                }
            }
        });
        ScrollPane scrollPane = new ScrollPane(list, game.getScrollPaneStyle());
        scrollPane.setSize(640,720);
        scrollPane.setScrollingDisabled(true, false);
        //scrollPane.setOverscroll(false, false);
        addActor(scrollPane);
        addActor(label);
        list.setSize(640,list.getItems().size*list.getStyle().font.getLineHeight());
    }


    @Override
    public void act(float delta) {
        super.act(delta);

    }


    @Override
    public void init() {//nem volt jó az assetsmanagerben a json
/*        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

        Label text = new Label(reallyLongString, skin);
        text.setAlignment(Align.center);
        text.setWrap(true);
        Label text2 = new Label("This is a short string!", skin);
        text2.setAlignment(Align.center);
        text2.setWrap(true);
        Label text3 = new Label(reallyLongString, skin);
        text3.setAlignment(Align.center);
        text3.setWrap(true);

        Table scrollTable = new Table();
        scrollTable.add(text);
        scrollTable.row();
        scrollTable.add(text2);
        scrollTable.row();
        scrollTable.add(text3);

        ScrollPane scroller = new ScrollPane(scrollTable);

        Table table = new Table();
        table.setFillParent(true);
        table.add(scroller).fill().expand();

        addActor(table);*/

    }
}
