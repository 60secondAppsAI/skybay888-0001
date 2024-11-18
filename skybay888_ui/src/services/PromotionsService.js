import http from "../http-common"; 

class PromotionsService {
  getAllPromotionss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/promotions/promotionss`, searchDTO);
  }

  get(promotionsId) {
    return this.getRequest(`/promotions/${promotionsId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/promotions?field=${matchData}`, null);
  }

  addPromotions(data) {
    return http.post("/promotions/addPromotions", data);
  }

  update(data) {
  	return http.post("/promotions/updatePromotions", data);
  }
  
  uploadImage(data,promotionsId) {
  	return http.postForm("/promotions/uploadImage/"+promotionsId, data);
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

export default new PromotionsService();
