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
            <delays-table
            v-if="delayss && delayss.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:delayss="delayss"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-delayss="getAllDelayss"
             >

            </delays-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DelaysTable from "@/components/DelaysTable";
import DelaysService from "../services/DelaysService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DelaysTable,
  },
  data() {
    return {
      delayss: [],
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
    async getAllDelayss(sortBy='delaysId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DelaysService.getAllDelayss(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.delayss.length) {
					this.delayss = response.data.delayss;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching delayss:", error);
        }
        
      } catch (error) {
        console.error("Error fetching delays details:", error);
      }
    },
  },
  mounted() {
    this.getAllDelayss();
  },
  created() {
    this.$root.$on('searchQueryForDelayssChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDelayss();
    })
  }
};
</script>
<style></style>
