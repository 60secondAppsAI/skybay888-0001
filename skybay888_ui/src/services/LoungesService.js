import http from "../http-common"; 

class LoungesService {
  getAllLoungess(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/lounges/loungess`, searchDTO);
  }

  get(loungesId) {
    return this.getRequest(`/lounges/${loungesId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/lounges?field=${matchData}`, null);
  }

  addLounges(data) {
    return http.post("/lounges/addLounges", data);
  }

  update(data) {
  	return http.post("/lounges/updateLounges", data);
  }
  
  uploadImage(data,loungesId) {
  	return http.postForm("/lounges/uploadImage/"+loungesId, data);
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

export default new LoungesService();
