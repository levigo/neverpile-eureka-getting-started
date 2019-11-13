package com.levigo.jwt.client.api;

import com.levigo.jwt.shared.model.UrlSource;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.levigo.jadice.document.Document;
import com.levigo.jadice.web.client.PageView;
import com.levigo.jadice.web.client.reader.Reader;

public class JadiceApi {

	private PageView pageView;

	public JadiceApi(PageView pageView) {
		this.pageView = pageView;
		exposeJavaScriptApi();
	}

	/**
	 * Loads the document referenced by the passed url.
	 * 
	 * @param url
	 */
	public void loadDocument(final String url) {
		Reader r = new Reader();
		r.append(new UrlSource(url));
		r.complete(new AsyncCallback<Document>() {

			@Override
			public void onSuccess(Document doc) {
				pageView.setDocument(doc);
			}

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				Window.alert("Cant load document from \"" + url + "\".");
			}
		});
	}

	public native void exposeJavaScriptApi() /*-{
		var that = this;

		$wnd.getJadiceApi = function() {
		    return {
					loadDocument: function(url) {
						that.@com.levigo.jwt.client.api.JadiceApi::loadDocument(Ljava/lang/String;)(url);
					}
				}
		}
	}-*/;
}
