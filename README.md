Дневник
Kotlin.Android Studio

Адаптер RecyclerView:

    class NameAdapter(
        private val listener: ListenerDataBase
    ) : RecyclerView.Adapter<TaskViewHolder>() {
        private var items = mutableListOf<Task>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
            return TaskViewHolder(
                ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.bind(items[position], listener)
        }

        override fun getItemCount() = items.size
        }

ViewHolder RecyclerView:

    class NameViewHolder(private val binding: элемент RecyclerView) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) = with(binding) {
            textViewItemTaskName.text = task.nameTask
            textViewItemTaskTime.text = task.timeGenerationTask
                .format(DateTimeFormatter.ofPattern("HH:mm dd.MM.y"))
            checkBoxItemTask.isChecked = task.accomplishmentTask
            root.setOnClickListener {  \\слушатель кликов на элемент
                binding.checkBoxItemTask.isChecked = !binding.checkBoxItemTask.isChecked
            }
        }
    }

Класс с локальной базой данных:

    class DatabaseTask(context: Context) : SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

        companion object {
            private const val DATABASE_NAME = "DataBaseTask"
            private const val DATABASE_VERSION = 1
            private const val TABLE_NAME = "Tasks"
            private const val NAME_TASK_COLUMN = "Task"
            private const val TIME_COLUMN = "Time"
            private const val CONDITION_COLUMN = "Сondition"
            private const val ID_COLUMN = "id"
        }

    Создание таблицы

        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(
                "CREATE TABLE $TABLE_NAME(" +
                    "$ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$NAME_TASK_COLUMN TEXT, " +
                    "$TIME_COLUMN NUMERIC, " +
                    "$CONDITION_COLUMN NUMERIC)"
            )
        }

    Потои узнаем и применим)

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    Добавление данных в БД

        fun addTask(nameTask: String, timeGenerationTask: LocalDateTime, accomplishmentTask: Boolean) {
            writableDatabase.insert(
                TABLE_NAME,
                null,
                ContentValues().apply {
                    put(NAME_TASK_COLUMN, nameTask)
                    put(TIME_COLUMN, timeGenerationTask.toString())
                    put(CONDITION_COLUMN, accomplishmentTask)
                }
            )
        }

    Получание данных их БД

        fun getTasks(): List<Task> {
            val cursor = readableDatabase.query(
                TABLE_NAME,
                arrayOf(ID_COLUMN, NAME_TASK_COLUMN, TIME_COLUMN, CONDITION_COLUMN), //Массив столбцов, из которых забираются данные
                null,
                null,
                null,
                null,
                null,
            )
            val tasks = mutableListOf<Task>()

            with(cursor) {
                if (moveToFirst()) {
                    do {
                        tasks.add(
                            Task(
                                id = getInt(getColumnIndexOrThrow(ID_COLUMN)),
                                nameTask = getString(getColumnIndexOrThrow(NAME_TASK_COLUMN)),
                                timeGenerationTask = LocalDateTime.parse(
                                    getString(getColumnIndexOrThrow(TIME_COLUMN))
                                ),
                                accomplishmentTask = getInt(getColumnIndexOrThrow(CONDITION_COLUMN)) > 0
                            )
                        )
                    } while (moveToNext())
                }
                close()
            }
            close()
            return tasks
        }

    Обновление данных в БД

        fun upgradeTask(id: Int, task: Task) {
            writableDatabase.update(
                TABLE_NAME,
                ContentValues().apply {
                    put(NAME_TASK_COLUMN, task.nameTask)
                    put(TIME_COLUMN, task.timeGenerationTask.toString())
                    put(CONDITION_COLUMN, task.accomplishmentTask)
                },
                "id = ?",
                arrayOf(id.toString())
            )
        }
    }

Создание Preferences

    private val preferencesSingIn = context
        .getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

Добавление в Preferences

    var login: String
        get() = preferencesSingIn.getString(LOGIN_KEY, null) ?: ""
        set(value) = preferencesSingIn
        .edit()
        .putString(LOGIN_KEY, value)
        .apply()

Очистка Preferences

        fun clearPreferences() {
            preferencesSingIn
                .edit()
                .clear()
                .apply()
        }

Создание скролищего AppBar:

        <com.google.android.material.appbar.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <com.google.android.material.appbar.CollapsingToolbarLayout     |
                android:id="@+id/collapsingToolbarProfile"                  |
                android:layout_width="match_parent"                         |
                android:layout_height="wrap_content"                        |
                app:layout_scrollFlags="scroll|exitUntilCollapsed"          |
                app:contentScrim="@color/white"                             |
                app:expandedTitleTextAppearance="@style/titleProfile"       }  часть до скрола
                app:expandedTitleGravity="center_horizontal|bottom">        |
                                                                            |
                                                                            |
                <                                                           |
                    ...                                                     |
                >                                                           |

                <com.google.android.material.appbar.MaterialToolbar         //после скрола
                    android:layout_width="match_parent"
                    android:layout_height="?actionBarSize"
                    android:background="@android:color/transparent"
                    app:layout_collapseMode="pin"
                    app:titleMarginStart="30dp"
                    app:titleMarginEnd="35dp"/>
            </com.google.android.material.appbar.CollapsingToolbarLayout>

        </com.google.android.material.appbar.AppBarLayout>

Splash-код:

    Handler().postDelayed({
                startActivity(Intent(this, OnBordingActivity::class.java))
                finish()
            }, 5000
            )

Создание retrofita

    val retrofit = Retrofit.Builder()
        .baseUrl("ссылка на сервер, https")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create('интерфейс с методами запросов'::class.java)

Создание запроса

    ApiService.retrofit.'метод запроса'()
                    .enqueue(object : Callback<'то что возвращает сервер'> {
                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            срабатывает при удачном запросе
                            response.code() //получение кода от сервера
                        }

                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            срабатывает при ошибке
                        }
                })

Слушатель свайпов ViewPager2

    binding.viewPagerTasks.registerOnPageChangeCallback(
                object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        //получение текущего номера фрагмента(с 0)
                    }
                }
            )

Связывание ViewPager2 и TabLayout

    TabLayoutMediator(binding.tabLayoutTasks, binding.viewPagerTasks) { tab, position ->
                tab.text = when (position) {
                    0 -> getString(R.string.category_work)
                    1 -> getString(R.string.category_study)
                    2 -> getString(R.string.category_entertainments)
                    else -> null
                }
            }.attach()