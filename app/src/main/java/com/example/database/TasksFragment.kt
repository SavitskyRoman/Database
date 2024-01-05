package com.example.database
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.checkPermission
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.database.*
import com.example.database.databinding.FragmentTasksBinding
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader
import java.io.ObjectOutputStream
class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.inflateMenu(R.menu.tasks_menu)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(TasksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

      //  binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_close_24)

        //viewModel.checkStatedListModule()

        val adapter = TaskItemAdapter { taskId ->
            val action = TasksFragmentDirections.actionTasksFragmentToChangeCardFragment(taskId)
            this.findNavController().navigate(action)
        }
        binding.taskList.adapter = adapter


        val adapterName = TaskNameAdapter { taskUnit ->
            viewModel.getUnitList(taskUnit).observe(viewLifecycleOwner, Observer {
                it?.let {
                    adapter.data = it
                    var forJson = mutableListOf<Task>()
                    forJson = it as MutableList<Task>
                    forJson.forEach{ println(it) }
                    try {
                        val filename = "myfile"
                        val fileContents = "Hello world!"
                        context?.openFileOutput(filename, Context.MODE_PRIVATE).use {
                            it?.write(forJson.toString().toByteArray())
//                            it?.write(fileContents.toByteArray())
                        }
//                        var fileStream = FileOutputStream("DatabaseEnglishCards")
//                        var os = ObjectOutputStream(fileStream)
//                        os.writeObject(it)
//                        os.close()

                    } catch (ex: java.lang.Exception){
                        ex.printStackTrace()
                    }

                     println(Json.encodeToString(it.get(0)))
                    println(Json.encodeToString(it))
//                    val gson = Json.encodeToString(forJson)
//                    println("From adapterName: $gson")
                }
            })
        }

        binding.taskNameList.adapter = adapterName


        viewModel.unitNameFromDatabase.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterName.data = it

               val gson = Json.encodeToString(it)
                println(gson)

                }

        })


        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.taskForDelArrayForRecycleView = it
                viewModel.firstStartApp()
            }
        })

        binding.quizButton.setOnClickListener {
            if (viewModel.checkStatedListModule() && viewModel.nameUnitToQuizFragment.isNotEmpty()) {
                val action =
                    TasksFragmentDirections.actionTasksFragmentToQuizFragment(viewModel.nameUnitToQuizFragment)
                view.findNavController().navigate(action)
            } else {
                Toast.makeText(context, "Тест пройден", Toast.LENGTH_SHORT).show()
                viewModel.update()
            }

        }
        binding.btnStorage.setOnClickListener { checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_PER) }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        requireActivity().finish()
                    }
                }
            }
            )

        return view
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(binding.root.context, permission) == PackageManager.PERMISSION_DENIED) {
            requestPermissions( arrayOf(permission), requestCode)
        } else {
            Toast.makeText(binding.root.context, "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_PER) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(binding.root.context, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(binding.root.context, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    companion object{
        private const val STORAGE_PER=101
    }


}
/* <TextView
            android:id="@+id/tasks"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{viewModel.tasksString}" />*/
//

