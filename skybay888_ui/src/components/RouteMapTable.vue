
<template>
  <div class="content">
    <!-- search bar -->
    <div class="row my-3 justify-content-end">
      <div class="col-8"></div>
       <div class="col-auto">
        <!-- Header Search Input -->
        <a-input-search class="header-search" :class="searchLoading ? 'loading' : ''" placeholder="Search by BusinessUnit#, Location#, Operator#, City, State, FirstName, LastName…"
          @search="onSearch" :loading='searchLoading' v-model="searchQuery">
          <svg slot="prefix" width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path fill-rule="evenodd" clip-rule="evenodd"
              d="M8 4C5.79086 4 4 5.79086 4 8C4 10.2091 5.79086 12 8 12C10.2091 12 12 10.2091 12 8C12 5.79086 10.2091 4 8 4ZM2 8C2 4.68629 4.68629 2 8 2C11.3137 2 14 4.68629 14 8C14 9.29583 13.5892 10.4957 12.8907 11.4765L17.7071 16.2929C18.0976 16.6834 18.0976 17.3166 17.7071 17.7071C17.3166 18.0976 16.6834 18.0976 16.2929 17.7071L11.4765 12.8907C10.4957 13.5892 9.29583 14 8 14C4.68629 14 2 11.3137 2 8Z"
              fill="#111827" />
          </svg>
        </a-input-search>
        <!-- / Header Search Input -->
      </div>
    </div>
    <div class="row">
      <div class="col-12">
        <card>
          <template slot="header">
            <div class="row justify-content-between align-items-between mx-3">

              <h5 class="card-title">Route Maps</h5>
              
              <div>
                  <base-button class="btn btn-primary" @click="modalRouteMaps = true">Add</base-button>
              </div>
              
              <modal :show.sync="modalRouteMaps">
                <template slot="header">
                  <h5 class="modal-title" id="exampleModalLabel">Add RouteMap</h5>
                </template>
                <div>
                  <form @submit.prevent>
  <base-input label="RouteMapId" type="text" placeholder="Enter RouteMapId" v-model="routeMapToAdd.routeMapId"></base-input>
  <base-input label="Distance" type="text" placeholder="Enter Distance" v-model="routeMapToAdd.distance"></base-input>
  <base-input label="Duration" type="text" placeholder="Enter Duration" v-model="routeMapToAdd.duration"></base-input>
                  </form>
                </div>
                <template slot="footer">
                  <base-button type="primary" @click="handleAddSubmitted()">Save</base-button>
                </template>
              </modal>
            </div>
          </template>


          <!-- Conditionally render the view based on the 'isTableView' flag -->
          <div v-if="isTableView">
            <!-- Render the existing Table View -->
            <a-table :columns="columns" :data-source="routeMaps" :row-key="record => record.RouteMapId" :pagination="pagination"
              :loading="searchLoading" @change="onTableChange" :scroll="{ x: 'max-content' }">



             <template slot="lastModified" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="createdOn" slot-scope="dataIndex">
              	{{ formatTime(dataIndex) }}
              </template>
              <template slot="blackOutStartDate" slot-scope="dataIndex">
              	{{ formatDate(dataIndex) }}
              </template>
            </a-table>
          </div>
          <div v-else>
            <!-- Render the Picture View  -->
            <RouteMapPictureView :routeMaps="routeMaps" />
          </div>

        </card>
      </div>
    </div>

  </div>
</template>

<script>
import Modal from "@/components/Modal";
import BaseButton from "@/components/BaseButton";
import BaseInput from "@/components/BaseInput";
import NotificationTemplate from "@/pages/Notifications/NotificationTemplate";
import { Card } from "@/components/Card";
import RouteMapService from "../services/RouteMapService";
import { ASelect, ASelectOption, AButton, Table, Pagination } from "ant-design-vue";
import RouteMapPictureView from './RouteMapPictureView.vue';


const routeMapsColumns = [
  "routeMapId",
  "year",
  "date",
  "competitionId",
  "routeMapId"
]

export default {
  props: {
    routeMaps: {
      type: Array,
      required: true,
    },
    totalElements: {
      type: Number,
      required: true,
    },
    page: {
      type: Number,
      required: true,
    },
  },
  components: {
    Card,
    Modal,
    BaseButton,
    BaseInput,
    Table,

    Pagination,
    RouteMapPictureView
  },

  data() {
    return {
      modalRouteMaps: false,
        isTableView: true,

      columns: [
        {
          title: 'Route Map Id',
		dataIndex: 'routeMapId',
          visible: true,
          scopedSlots: { customRender: 'routeMapId' },
          sorter: true
          //sorter: (a, b) => a.routeMapId - b.routeMapId,
          //sorter: (a, b) => a.routeMapId.localeCompare(b.routeMapId),
        },
        {
          title: 'Distance',
		dataIndex: 'distance',
          visible: true,
          scopedSlots: { customRender: 'distance' },
          sorter: true
          //sorter: (a, b) => a.distance - b.distance,
          //sorter: (a, b) => a.distance.localeCompare(b.distance),
        },
        {
          title: 'Duration',
		dataIndex: 'duration',
          visible: true,
          scopedSlots: { customRender: 'duration' },
          sorter: true
          //sorter: (a, b) => a.duration - b.duration,
          //sorter: (a, b) => a.duration.localeCompare(b.duration),
        },
      ],
      pagination: {
        current: 1,
        pageSize: 50,
        total: 0,
        showSizeChanger: true,
        showQuickJumper: true,
        showTotal: (total) => `Total ${total} routeMaps`,
      },

      routeMaps: [],
      routeMapToAdd: {},

      routeMapsTable: {
        columns: [...routeMapsColumns],
        data: [],
      },

      // New properties for sorting and searching
      sortBy: 'routeMapId',           // Column to sort by
      sortOrder: 'asc',     // Sort order (asc or desc)
      searchQuery: '',      // Search query
      searchLoading: false, // Initialize searchLoading property


    };
  },
  watch: {
    searchQuery: {
      handler: "handleSearchQueryChanged", // Call the fetchData method when searchQuery changes
      immediate: true, // Trigger immediately when the component is mounted
    },
  },

  methods: {

    async renderRouteMapsTable() {
      this.searchLoading = true; // Set searchLoading to true while fetching data
      this.searchLoading = false;

      this.pagination.total = this.totalElements;
      this.pagination.current = this.page;

      let routeMapsTableData = [];
      for (let i = 0; i < this.routeMaps.length; i++) {
        routeMapsTableData.push({
          id: i,
          routeMapId: this.routeMaps[i].routeMapId,
          year: this.routeMaps[i].year,
          date: this.routeMaps[i].date,
          competitionId: this.routeMaps[i].competitionId,
          routeMapId: this.routeMaps[i].routeMapId,
        });

      }
      this.searchLoading = false;
    },

    async onTableChange(pagination, filters, sorter) {
      if (sorter && sorter.field && sorter.order) {
        this.sortBy = sorter.field;
        if (sorter.order == "ascend") {
            this.sortOrder = "asc";
        } else {
            this.sortOrder = "desc";
        }
      }
      if (pagination) {
        this.pagination.current = pagination.current;
        this.pagination.pageSize = pagination.pageSize;
      }

	  this.$emit('get-all-route-maps',this.sortBy,this.sortOrder,this.pagination.current-1,this.pagination.pageSize);
      this.handleTableChanged()
    },
	
	initializeColumns() {
        this.columns = this.columns.filter(item => item.visible);
    },

    routingToFlightDetail(id) {
      this.$router.push({ name: 'FlightDetail', params: { flightId: id.toString() }})
    },
    routingToAirlineDetail(id) {
      this.$router.push({ name: 'AirlineDetail', params: { airlineId: id.toString() }})
    },
    routingToAirportDetail(id) {
      this.$router.push({ name: 'AirportDetail', params: { airportId: id.toString() }})
    },
    routingToPassengerDetail(id) {
      this.$router.push({ name: 'PassengerDetail', params: { passengerId: id.toString() }})
    },
    routingToTicketDetail(id) {
      this.$router.push({ name: 'TicketDetail', params: { ticketId: id.toString() }})
    },
    routingToBookingDetail(id) {
      this.$router.push({ name: 'BookingDetail', params: { bookingId: id.toString() }})
    },
    routingToSeatDetail(id) {
      this.$router.push({ name: 'SeatDetail', params: { seatId: id.toString() }})
    },
    routingToBaggageDetail(id) {
      this.$router.push({ name: 'BaggageDetail', params: { baggageId: id.toString() }})
    },
    routingToPaymentDetailDetail(id) {
      this.$router.push({ name: 'PaymentDetailDetail', params: { paymentDetailId: id.toString() }})
    },
    routingToAircraftDetail(id) {
      this.$router.push({ name: 'AircraftDetail', params: { aircraftId: id.toString() }})
    },
    routingToCrewDetail(id) {
      this.$router.push({ name: 'CrewDetail', params: { crewId: id.toString() }})
    },
    routingToRouteMapDetail(id) {
      this.$router.push({ name: 'RouteMapDetail', params: { routeMapId: id.toString() }})
    },
    routingToMealDetail(id) {
      this.$router.push({ name: 'MealDetail', params: { mealId: id.toString() }})
    },
    routingToCheckInDetail(id) {
      this.$router.push({ name: 'CheckInDetail', params: { checkInId: id.toString() }})
    },
    routingToFlightStatusDetail(id) {
      this.$router.push({ name: 'FlightStatusDetail', params: { flightStatusId: id.toString() }})
    },
    routingToLoungesDetail(id) {
      this.$router.push({ name: 'LoungesDetail', params: { loungesId: id.toString() }})
    },
    routingToDelaysDetail(id) {
      this.$router.push({ name: 'DelaysDetail', params: { delaysId: id.toString() }})
    },
    routingToTicketPriceDetail(id) {
      this.$router.push({ name: 'TicketPriceDetail', params: { ticketPriceId: id.toString() }})
    },
    routingToCustomerServiceRequestDetail(id) {
      this.$router.push({ name: 'CustomerServiceRequestDetail', params: { customerServiceRequestId: id.toString() }})
    },
    routingToPromotionsDetail(id) {
      this.$router.push({ name: 'PromotionsDetail', params: { promotionsId: id.toString() }})
    },
    
    handleSearchQueryChanged() {
    	console.log("handleSearchQueryChanged CALLED FROM @search")
    	this.$root.$emit('searchQueryForRouteMapsChanged', this.searchQuery);
		//this.renderRouteMapsTable();
    },

    onSearch(value) {
      console.log(value);
      this.searchQuery = value; // Update searchQuery when the user types
    },

    toggleView() {
      this.isTableView = !this.isTableView;
    },
	
	async handleAddSubmitted() {
      this.modalRouteMaps = false;

      const currentDate = new Date().getTime();
      this.routeMapToAdd.created = currentDate;

      const jsonData = JSON.stringify(this.routeMapToAdd);
      console.log(jsonData);
      
      const res = await RouteMapService.addRouteMap(jsonData);

      if (res.status === 200) {
        this.$notify({
          component: NotificationTemplate,
          icon: "tim-icons icon-bell-55",
          type: "success",
          timeout: 3000,
        });
      }
	},
	
	handleTableChanged() {
	},
	
	formatTime(dateString) {
      if(dateString !== null){
        const date = new Date(dateString);
      return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}-${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')} EST`;
      }
      // Format the date here as needed, for example:
    },  
    
 formatDate(dateString) {
    if (dateString !== null) {
	    const date = new Date(dateString);
	    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
	    const day = String(date.getDate()).padStart(2, '0');
	    const monthAbbreviation = months[date.getMonth()];
	    const year = date.getFullYear();
	    return `${day} ${monthAbbreviation} ${year}`;
  	}
  	// Handle the case when dateString is null or invalid
  	return '';
    
   },

  },
  mounted() {
  	this.initializeColumns();
    this.renderRouteMapsTable();
  }
};
</script>

<style>
.modal-dialog {
  margin-top: -300px;
}
.ant-table-row  {
	text-align: center;
}
.ant-table-thead th span {
	text-align: center;
	width: 100%
}
.ant-table-fixed td span {
    text-align: center;
}
.header-search {
  width: 300px !important;
  margin-left: auto !important;
}
</style>
