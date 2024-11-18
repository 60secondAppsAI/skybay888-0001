import http from "../http-common"; 

class RouteMapService {
  getAllRouteMaps(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/routeMap/routeMaps`, searchDTO);
  }

  get(routeMapId) {
    return this.getRequest(`/routeMap/${routeMapId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/routeMap?field=${matchData}`, null);
  }

  addRouteMap(data) {
    return http.post("/routeMap/addRouteMap", data);
  }

  update(data) {
  	return http.post("/routeMap/updateRouteMap", data);
  }
  
  uploadImage(data,routeMapId) {
  	return http.postForm("/routeMap/uploadImage/"+routeMapId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new RouteMapService();
