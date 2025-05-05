import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import client_1 from "./connect-client.default.js";
import type StatsWrapper_1 from "./de/plimplom/gamba/server/stats/StatsWrapper.js";
async function getDeathrollData_1(init?: EndpointRequestInit_1): Promise<StatsWrapper_1> { return client_1.call("StatsController", "getDeathrollData", {}, init); }
async function getFullData_1(init?: EndpointRequestInit_1): Promise<StatsWrapper_1> { return client_1.call("StatsController", "getFullData", {}, init); }
async function getHighLowData_1(init?: EndpointRequestInit_1): Promise<StatsWrapper_1> { return client_1.call("StatsController", "getHighLowData", {}, init); }
export { getDeathrollData_1 as getDeathrollData, getFullData_1 as getFullData, getHighLowData_1 as getHighLowData };
