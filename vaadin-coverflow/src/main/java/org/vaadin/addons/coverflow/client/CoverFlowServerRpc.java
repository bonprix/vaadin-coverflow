package org.vaadin.addons.coverflow.client;

import com.vaadin.shared.communication.ServerRpc;

public interface CoverFlowServerRpc extends ServerRpc {

	public void click(final int reference);

}
