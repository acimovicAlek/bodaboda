using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace BodaBodaServer.Migrations
{
    public partial class Initial : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Users",
                columns: table => new
                {
                    UserId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Username = table.Column<string>(nullable: false),
                    Password = table.Column<string>(nullable: false),
                    UserType = table.Column<string>(nullable: false),
                    FirstName = table.Column<string>(nullable: true),
                    LastName = table.Column<string>(nullable: true),
                    Email = table.Column<string>(nullable: false),
                    PhoneNumber = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Users", x => x.UserId);
                });

            migrationBuilder.CreateTable(
                name: "Locations",
                columns: table => new
                {
                    LocationId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Longitude = table.Column<double>(nullable: false),
                    Latitude = table.Column<long>(nullable: false),
                    LocationType = table.Column<string>(nullable: false),
                    UserId = table.Column<long>(nullable: false),
                    TTL = table.Column<long>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Locations", x => x.LocationId);
                    table.ForeignKey(
                        name: "FK_Locations_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "PaymentOptions",
                columns: table => new
                {
                    PaymentOptionId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    OptionType = table.Column<string>(nullable: true),
                    Details = table.Column<string>(nullable: true),
                    UserId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_PaymentOptions", x => x.PaymentOptionId);
                    table.ForeignKey(
                        name: "FK_PaymentOptions_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "TaxiPrices",
                columns: table => new
                {
                    TaxiPriceId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    UserId = table.Column<long>(nullable: false),
                    StartingPrice = table.Column<double>(nullable: false),
                    PricePerUnit = table.Column<double>(nullable: false),
                    PricePerHour = table.Column<double>(nullable: true),
                    SpecialPrice = table.Column<double>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_TaxiPrices", x => x.TaxiPriceId);
                    table.ForeignKey(
                        name: "FK_TaxiPrices_Users_UserId",
                        column: x => x.UserId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Trips",
                columns: table => new
                {
                    TripId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Status = table.Column<string>(nullable: false),
                    Price = table.Column<double>(nullable: false),
                    Paid = table.Column<short>(nullable: false),
                    TripStart = table.Column<DateTime>(nullable: false),
                    TripEnd = table.Column<DateTime>(nullable: true),
                    StartingLocationId = table.Column<long>(nullable: false),
                    EndingLocationId = table.Column<long>(nullable: false),
                    CustomerId = table.Column<long>(nullable: false),
                    TaxiId = table.Column<long>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Trips", x => x.TripId);
                    table.ForeignKey(
                        name: "FK_Trips_Users_CustomerId",
                        column: x => x.CustomerId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Trips_Locations_EndingLocationId",
                        column: x => x.EndingLocationId,
                        principalTable: "Locations",
                        principalColumn: "LocationId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Trips_Locations_StartingLocationId",
                        column: x => x.StartingLocationId,
                        principalTable: "Locations",
                        principalColumn: "LocationId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Trips_Users_TaxiId",
                        column: x => x.TaxiId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Payments",
                columns: table => new
                {
                    PaymentId = table.Column<long>(nullable: false)
                        .Annotation("MySQL:AutoIncrement", true),
                    Description = table.Column<string>(nullable: true),
                    Amount = table.Column<double>(nullable: false),
                    Status = table.Column<string>(nullable: true),
                    TimeStamp = table.Column<DateTime>(nullable: false),
                    TripId = table.Column<long>(nullable: false),
                    PayerId = table.Column<long>(nullable: false),
                    PayeeId = table.Column<long>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Payments", x => x.PaymentId);
                    table.ForeignKey(
                        name: "FK_Payments_Users_PayeeId",
                        column: x => x.PayeeId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Payments_Users_PayerId",
                        column: x => x.PayerId,
                        principalTable: "Users",
                        principalColumn: "UserId",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_Payments_Trips_TripId",
                        column: x => x.TripId,
                        principalTable: "Trips",
                        principalColumn: "TripId",
                        onDelete: ReferentialAction.Cascade);
                });

            //migrationBuilder.CreateIndex(
            //    name: "IX_Locations_UserId",
            //    table: "Locations",
            //    column: "UserId",
            //    unique: false);

            migrationBuilder.CreateIndex(
                name: "IX_PaymentOptions_UserId",
                table: "PaymentOptions",
                column: "UserId");

            migrationBuilder.CreateIndex(
                name: "IX_Payments_PayeeId",
                table: "Payments",
                column: "PayeeId");

            migrationBuilder.CreateIndex(
                name: "IX_Payments_PayerId",
                table: "Payments",
                column: "PayerId");

            migrationBuilder.CreateIndex(
                name: "IX_Payments_TripId",
                table: "Payments",
                column: "TripId");

            migrationBuilder.CreateIndex(
                name: "IX_TaxiPrices_UserId",
                table: "TaxiPrices",
                column: "UserId",
                unique: true);

            migrationBuilder.CreateIndex(
                name: "IX_Trips_CustomerId",
                table: "Trips",
                column: "CustomerId");

            migrationBuilder.CreateIndex(
                name: "IX_Trips_EndingLocationId",
                table: "Trips",
                column: "EndingLocationId");

            migrationBuilder.CreateIndex(
                name: "IX_Trips_StartingLocationId",
                table: "Trips",
                column: "StartingLocationId");

            migrationBuilder.CreateIndex(
                name: "IX_Trips_TaxiId",
                table: "Trips",
                column: "TaxiId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "PaymentOptions");

            migrationBuilder.DropTable(
                name: "Payments");

            migrationBuilder.DropTable(
                name: "TaxiPrices");

            migrationBuilder.DropTable(
                name: "Trips");

            migrationBuilder.DropTable(
                name: "Locations");

            migrationBuilder.DropTable(
                name: "Users");
        }
    }
}
