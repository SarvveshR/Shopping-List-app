package com.example.myshoppinglistapp

import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyshopppingApp(){
    var sItems by remember{ mutableStateOf( listOf<ShoppingItem>()) }//This sItems is  a state that is a list  that contains objects of shopping list as elements
    var showdiaglog by  remember{mutableStateOf(false)}
    var itemname by remember{ mutableStateOf("") }
    var itemquantity by remember{ mutableStateOf("") }


    Column(modifier= Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center  ){

        Button(onClick = {showdiaglog=true }, modifier= Modifier.align(Alignment.CenterHorizontally))/*Modifier used*/{


            Text(text ="Add Item" )

        }








        LazyColumn(modifier= Modifier.fillMaxSize()) {//Vertical scrolling composable which displays items in  order in collum wise
            items(sItems){//"it" represents Each element of the Sitems list which is the objects of the class shopping tiems
                //ShoppingAddItems(Item_obj = it, OnEditClick = {}, OnClearClick = {})//keeps calling this until all the objects are over
                Item->//Now this acts like the element of sItems at that instance
                if(Item.IsEditing==true){//eveything here is applicable for all the items inside sitems which means we nned this only for the objecct we r working on
                    ShoppingEditItem(item = Item, onEditComplete = {//called when clicked on save button
                        Editedname,Editedquantity->// parameters of onEditcomplete we can use diff names and they are the modified arguements too coz it's a variable part of basictextfield
                         sItems=sItems.map{it.copy(IsEditing = false)}//it is the current object/element and keeps changing during iteration we are copying Isediting to false and mapping the edited element to all the elements and asigning it to sitems(usually we make a new variable but in this case this is tthe option) which basically chuks the previous list asiigns a new one
                        val editedItem= sItems.find { it.id==Item.id }// we r pin pointing the itemm we are wokring on, "it" represents the elements of the sitems list whivh are objects and  item repreent s the current element in the laszy column that takes elements from sitems
                        editedItem?.let {//let does the operation without disturing the object's original state
                            it.name=Editedname//the modified name by the user in basic text field is usd here with a different name
                            it.quantity=Editedquantity//same here

                        }

                    })
                }
                else{
                    ShoppingAddItems(Item_obj = Item, OnEditClick = {//called when clicked on Icon button
                        //SO for every Item which is the currrent eleement from sItems and "it" is also the the itermating variable  of sitems ,bascially changing Isediting to true for the obejecct eee r currently on
                        //for immutable lists when something is changed the orginal list is replace by is new one  instead of modifying the orginal list like in mutbalelsit
                        sItems=sItems.map{it.copy(IsEditing = it.id==Item.id)}

                    }, OnClearClick = {//Called when clicked on delete button
                        sItems=sItems-Item

                    })
                }

            }

        }


    }
    //We are manipulating alert dialog as out shopping lsit addking thing
    if(showdiaglog==true){
        AlertDialog(onDismissRequest = {showdiaglog=false }
                    , confirmButton = {
                        Row(modifier=Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
                            Button(onClick = {//A empty lambda function which is a parameter that is basicallly called when Button is clicked hence executes the code in it
                                             if(itemname.isNotBlank()){
                                                 var newItem=ShoppingItem(//Creating objects of shopping list
                                                     id=sItems.size+1, itemname, itemquantity.toInt(), false
                                                 )
                                                 sItems+=newItem//adding obejcts to the list,in immutable list when something is added as it is non changeable it deteles the old list and creates a new one instead of editing the original one
                                                 showdiaglog=false
                                                 itemname="" 
                                                 itemquantity=""

                                             }
                                                if( itemname.isBlank()){
                                                    showdiaglog=false
                                                    

                                                }

                                            }
                                ,modifier=Modifier.padding(8.dp)) {
                                Text(text="Add")



                            }
                            Button(onClick = {showdiaglog=false}) {
                                Text("Cancel")
                            }




                        }

            },

            //We use the confirm butttons of a dialog as Add and cancel buttons
                    title = { Text(text = "Add Shopping List Items")},//adding title
                        text = {// we bascically manipulated text value paramemeter here that takes in composable and we stuffed in all our contents in it which are composables
                        Column {
                            OutlinedTextField(value =itemname ,
                                onValueChange ={itemname= it},
                                singleLine = true,
                                modifier= Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                label = { Text(text = "Item name", color = Color.Gray.copy(alpha = 0.8f))}


                            )
                            OutlinedTextField(value = itemquantity,
                                onValueChange ={itemquantity=it},
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                label = { Text(text = "Item QTY", color = Color.Gray.copy(alpha = 0.6f))})






                        }
                    }
                   )

                    }





    }


data class ShoppingItem(val id:Int,var name:String, var quantity:Int,var IsEditing:Boolean)//class that only stores data


@Composable
fun ShoppingAddItems(Item_obj:ShoppingItem,OnEditClick: ()->Unit,OnClearClick:()->Unit){//This functiion is like the template for lazy column and how it is gonna display each object
    Row(modifier= Modifier//the lambda functions as parameters is called inside a the onlick of a button so when I give the arguement code and, during clicking of that button the fucntion is called inside onClick and the arguement code is executed .
        .padding(8.dp)
        .fillMaxSize()
        .border(border = BorderStroke(2.dp, Color.Blue), shape = RoundedCornerShape(20))
        ,
        horizontalArrangement = Arrangement.SpaceBetween){


        Text(text ="${Item_obj.name}",modifier=Modifier.padding(8.dp))
        Text(text ="QTY:${Item_obj.quantity}",modifier=Modifier.padding(8.dp))

        Row(horizontalArrangement = Arrangement.Absolute.Right) {
            IconButton(onClick = { OnEditClick() }) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit button")

            }
            IconButton(onClick = { OnClearClick() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Button")

            }
        }

    }

    }

@Composable//THis function is for the editing part when Icon button 1 is clicked
fun ShoppingEditItem(item:ShoppingItem,onEditComplete:(String,Int)->Unit){

    var editname by remember{ mutableStateOf(item.name) }
    var editquantity by remember{ mutableStateOf(item.quantity.toString()) }
    var IsEditing by remember{ mutableStateOf(item.IsEditing) }



    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .background(Color.White),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {


        Column {
            BasicTextField(value = editname, onValueChange = {editname=it}, modifier = Modifier.wrapContentSize())
            BasicTextField(value = editquantity, onValueChange ={editquantity=it},modifier=Modifier.wrapContentSize() )



        }

        Button(onClick = {
            IsEditing=false
            onEditComplete(editname,editquantity.toIntOrNull()?:1)
            }
        ) {
                Text(text = "Save")

            }



    }
}

















