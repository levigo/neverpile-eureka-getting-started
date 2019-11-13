package com.levigo.jwt.client;

import java.io.IOException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.levigo.jadice.web.client.PageView;
import com.levigo.jadice.web.client.util.context.Context;
import com.levigo.jadice.web.conn.client.ServerConnectionBuilder;
import com.levigo.jwt.client.api.JadiceApi;
import com.levigo.jwt.client.ui.JadiceWidget;

/**
 * This is the applications {@link EntryPoint} as defined in the
 * "Application.gwt.xml". It initializes the {@link JadiceWidget} and adds it to
 * {@link RootPanel}.
 */
public class ApplicationEntryPoint implements EntryPoint {

  private JadiceWidget jadiceWidget;

  private JadiceApi jadiceApi;

  @Override
  public void onModuleLoad() {

    initServerConnection();

    jadiceWidget = new JadiceWidget();

    // Adds the jadiceWidget to the "Viewer"-div of the rootpanel.
    RootPanel rootPanel = RootPanel.get("Viewer");
    rootPanel.add(jadiceWidget);

    initJadiceApi();

    GWT.log("jwt loaded");
  }

  /**
   * For the tutorial we deactivate websocket communication as not supported by
   * jetty.
   */
  private void initServerConnection() {
    try {
      new ServerConnectionBuilder().setWebSocketEnabled(false).build();
    } catch (IOException e) {
      e.printStackTrace();
      Window.alert("Error initializing server-connection.");
    }
  }

  private void initJadiceApi() {
    PageView pageView = jadiceWidget.getPageView();
    jadiceApi = new JadiceApi(pageView);
    // add the JadiceApi to the context of the JadiceWidget.
    Context.get(jadiceWidget).add(jadiceApi);
  }
}