// Ionic Starter App
var db = new PouchDB('contacts', {adapter: 'websql', iosDatabaseLocation: 'data'});
var remoteCouch = false;
// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// Get Data from database with id from contact list

angular.module('starter', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if(window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})


.config(function($stateProvider, $urlRouterProvider) {
  $stateProvider
    .state('tabs', {
      url: '/tab',
      abstract: true,
      templateUrl: 'templates/tabs.html'
    })

    .state('tabs.home', {
      url: '/home',
      cache: false,
      views: {
        'home-tab' : {
          templateUrl: 'templates/home.html'
        }
      }
    })

    .state('tabs.list', {
      url: '/list',
      cache: false,
      views: {
        'list-tab' : {
          templateUrl: 'templates/list.html',
          controller: 'ListController'
        }
      }
    })

    .state('tabs.detail', {
      url: '/list/:aId',
      views: {
        'list-tab' : {
          templateUrl: 'templates/detail.html',
          controller: 'DetailController'
        }
      }
    })

    .state('tabs.details', {
      url: '/calendar/:aId',
      views: {
        'calendar-tab' : {
          templateUrl: 'templates/detail.html',
          controller: 'DetailController'
        }
      }
    })

    .state('tabs.add', {
      url: '/add',
      views: {
        'list-tab' : {
          templateUrl: 'templates/add.html',
          controller: 'DetailController'
        }
      }
    })

    .state('tabs.calendar', {
      url: '/calendar',
      cache: false,
      views: {
        'calendar-tab' : {
          templateUrl: 'templates/calendar.html',
          controller: 'CalendarController'
        }
      }
    })

  $urlRouterProvider.otherwise('/tab/home');
})

.controller('CalendarController', ['$scope', '$state', function($scope, $state) {
      $scope.contacts = '';
      var data2 = JSON.parse(AndroidNative.getFavorites());
      $scope.contacts = data2;
      $scope.whichcontact=$state.params.aId;

      $scope.onItemDelete = function(item) {
        $scope.contacts.splice($scope.contacts.indexOf(item), 1);
      }

      $scope.toggleStar = function(item) {
        // Set up favorite
        AndroidNative.setFavorit(item.id, false);
        item.star = !item.star;
      }
}])

.controller('DetailController', ['$scope', '$state', function($scope, $state) {
   $scope.contacts = '';
   var data = JSON.parse(AndroidNative.getContact());
   $scope.contacts = data;
   $scope.whichcontact=$state.params.aId;

   // Contact create
   $scope.createContact = function(contact) {
     // Add contacts to database
     AndroidNative.addContact(
         contact.name,
         contact.lastname,
         contact.company,
         contact.phone,
         contact.email);

     // Show Android Toast
     AndroidNative.toast("Successfully added!!!");
   }

   // Contact update
   $scope.saveContact = function(contact) {
     // update contact by id
     AndroidNative.updateContact(
         contact.id,
         contact.name,
         contact.lastname,
         contact.company,
         contact.phone,
         contact.email);
   }

}])

.controller('ListController', ['$scope', '$state', function($scope, $state) {

    $scope.contacts = '';
    var data = JSON.parse(AndroidNative.getContact());
    // load data on instantiation*!/
    $scope.contacts = data;
    $scope.whichcontact=$state.params.aId;
    $scope.data = { showDelete: false, showReorder: false };

    $scope.onItemDelete = function(item) {
      AndroidNative.deleteContact(item.id);
      $scope.contacts.splice($scope.contacts.indexOf(item), 1);
    }

    $scope.toggleStar = function(item) {
      // Set up favorite
      AndroidNative.setFavorit(item.id, true);
      item.star = !item.star;
    }

    $scope.moveItem = function(item, fromIndex, toIndex) {
      $scope.contacts.splice(fromIndex, 1);
      $scope.contacts.splice(toIndex, 0, item);
    };

    $scope.backLinkClick = function () {
      window.location.reload();
    };

}]);
