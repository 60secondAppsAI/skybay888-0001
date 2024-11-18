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
            <customerServiceRequest-table
            v-if="customerServiceRequests && customerServiceRequests.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:customerServiceRequests="customerServiceRequests"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-customer-service-requests="getAllCustomerServiceRequests"
             >

            </customerServiceRequest-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CustomerServiceRequestTable from "@/components/CustomerServiceRequestTable";
import CustomerServiceRequestService from "../services/CustomerServiceRequestService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CustomerServiceRequestTable,
  },
  data() {
    return {
      customerServiceRequests: [],
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
    async getAllCustomerServiceRequests(sortBy='customerServiceRequestId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CustomerServiceRequestService.getAllCustomerServiceRequests(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.customerServiceRequests.length) {
					this.customerServiceRequests = response.data.customerServiceRequests;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching customerServiceRequests:", error);
        }
        
      } catch (error) {
        console.error("Error fetching customerServiceRequest details:", error);
      }
    },
  },
  mounted() {
    this.getAllCustomerServiceRequests();
  },
  created() {
    this.$root.$on('searchQueryForCustomerServiceRequestsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCustomerServiceRequests();
    })
  }
};
</script>
<style></style>
