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
            <ticketPrice-table
            v-if="ticketPrices && ticketPrices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:ticketPrices="ticketPrices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-ticket-prices="getAllTicketPrices"
             >

            </ticketPrice-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import TicketPriceTable from "@/components/TicketPriceTable";
import TicketPriceService from "../services/TicketPriceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    TicketPriceTable,
  },
  data() {
    return {
      ticketPrices: [],
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
    async getAllTicketPrices(sortBy='ticketPriceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await TicketPriceService.getAllTicketPrices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.ticketPrices.length) {
					this.ticketPrices = response.data.ticketPrices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching ticketPrices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching ticketPrice details:", error);
      }
    },
  },
  mounted() {
    this.getAllTicketPrices();
  },
  created() {
    this.$root.$on('searchQueryForTicketPricesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllTicketPrices();
    })
  }
};
</script>
<style></style>
