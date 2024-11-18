<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <promotions-table
            v-if="promotionss && promotionss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:promotionss="promotionss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-promotionss="getAllPromotionss"
             >

            </promotions-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import PromotionsTable from "@/components/PromotionsTable";
import PromotionsService from "../services/PromotionsService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    PromotionsTable,
  },
  data() {
    return {
      promotionss: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllPromotionss(sortBy='promotionsId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await PromotionsService.getAllPromotionss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.promotionss.length) {
					this.promotionss = response.data.promotionss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching promotionss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching promotions details:", error);
      }
    },
  },
  mounted() {
    this.getAllPromotionss();
  },
  created() {
    this.$root.$on('searchQueryForPromotionssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllPromotionss();
    })
  }
};
</script>
<style></style>
