import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import client_1 from "./connect-client.default.js";
async function getGuildName_1(init?: EndpointRequestInit_1): Promise<string> { return client_1.call("GambaConfigClient", "getGuildName", {}, init); }
export { getGuildName_1 as getGuildName };
